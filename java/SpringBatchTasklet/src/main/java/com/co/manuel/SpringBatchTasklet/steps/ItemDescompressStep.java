package com.co.manuel.SpringBatchTasklet.steps;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import com.co.manuel.SpringBatchTasklet.helpers.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemDescompressStep implements Tasklet {

  @Value("${server.folders.unzip}")
  private String unZipFolder;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init DESCOMPRESS Step ------ ");

    Object objStringPath = chunkContext.getStepContext().getJobParameters().get(Constants.STRING_PATH);
    Object objIsReplaceFiles = chunkContext.getStepContext().getJobParameters().get(Constants.IS_REPLACE_FILES);
    if (objStringPath == null) {
      log.info("------ Not parameter for path - Finish DESCOMPRESS Step ------ ");
      return RepeatStatus.FINISHED;
    }
    Boolean isReplaceFiles = objIsReplaceFiles == null ? false : Boolean.valueOf(objIsReplaceFiles.toString());
    /*
     * For testing, get the resource folder
     * 
     * @Autowired
     * private ResourceLoader resourceLoader;
     *
     * Resource resourceZipFile = resourceLoader
     * .getResource("classpath:files" + getDefault().getSeparator() +
     * "persons.zip");
     */

    Path zipFilePath = Path.of(objStringPath.toString());
    Path destinationPath = Path.of(unZipFolder, zipFilePath.getName(zipFilePath.getNameCount() - 1).toString());
    Files.createDirectories(destinationPath);
    Path newPath = null;
    // Read the zip File
    try (ZipInputStream zis = new ZipInputStream(
        new BufferedInputStream(Files.newInputStream(zipFilePath)))) {

      ZipEntry entry;

      while ((entry = zis.getNextEntry()) != null) {
        newPath = resolveZipEntry(destinationPath, entry);

        if (entry.isDirectory()) {
          Files.createDirectories(newPath);
        } else {
          // Create parent directories if needed
          createFileDirectory(newPath, isReplaceFiles);
          try (OutputStream os = Files.newOutputStream(newPath)) {
            zis.transferTo(os);
          }
        }
        zis.closeEntry();
      }
      zis.close();
    }
    chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext().put(Constants.FILE_PATH_TO_PROCESS, newPath.toString());
    log.info("------ Finish DESCOMPRESS Step ------ ");
    return RepeatStatus.FINISHED;
  }

  private void createFileDirectory(Path newFile, boolean isReplaceFiles) throws IOException {
    if (isReplaceFiles) {
      Files.deleteIfExists(newFile);
    }
    if (Files.exists(newFile)) {
      log.info("The file: " + newFile + " exist, don't replaced.");
    } else {
      Files.createDirectories(newFile.getParent());
    }

  }

  private Path resolveZipEntry(Path targetDir, ZipEntry entry) throws IOException {
    Path resolvedPath = targetDir.resolve(entry.getName()).normalize();

    if (!resolvedPath.startsWith(targetDir)) {
      throw new IOException("Bad zip entry: " + entry.getName());
    }

    return resolvedPath;
  }

}
