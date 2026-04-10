package com.co.manuel.SpringBatchTasklet.steps;

import static java.lang.System.lineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemDescompressStep implements Tasklet {

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init DESCOMPRESS Step ------ ");

    Resource resource = resourceLoader.getResource("classpath:files" + lineSeparator() + "person.zip");
    String filePath = resource.getFile().getPath();

    // Read the zip File
    ZipFile zipFile = new ZipFile(filePath);
    // Destination path
    File destDir = new File(resource.getFile().getParent(), "destination");

    if (!destDir.exists()) {
      destDir.mkdir();
    }

    // Read the zip file
    Enumeration<? extends ZipEntry> entries = zipFile.entries();

    while (entries.hasMoreElements()) {
      ZipEntry zipEntry = entries.nextElement();
      File file = new File(destDir, zipEntry.getName());

      if (zipEntry.isDirectory()) {
        file.mkdir();
      } else {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) > 0) {
          fileOutputStream.write(buffer, 0, length);
        }
        fileOutputStream.close();
        inputStream.close();
      }
    }

    zipFile.close();

    log.info("------ Finish DESCOMPRESS Step ------ ");

    return RepeatStatus.FINISHED;
  }

}
