package com.co.manuel.SpringBatchTasklet.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.co.manuel.SpringBatchTasklet.helpers.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BatchController {

  private final JobLauncher jobLauncher;

  private final Job job;

  @Value("${server.folders.zip}")
  private String zipFolder;

  public BatchController(JobLauncher jobLauncher, Job job) {
    this.jobLauncher = jobLauncher;
    this.job = job;
  }

  @PostMapping("/uploadFile")
  public ResponseEntity<?> receiveFile(@RequestParam(name = "file") MultipartFile multipartFile) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    String currentDate = LocalDateTime.now().format(formatter);
    // Normalize filename (avoid path traversal)
    String fileName = currentDate + "_"
        + Paths.get(multipartFile.getOriginalFilename()).getFileName().toString();
    if (multipartFile.isEmpty()) {
      return ResponseEntity.badRequest().body("File is empty");
    }
    if (!multipartFile.getOriginalFilename().endsWith(".zip")) {
      return ResponseEntity.badRequest().body("Only ZIP files are allowed");
    }
    try {
      Files.createDirectories(Paths.get(zipFolder));

      Path target = Paths.get(zipFolder).resolve(fileName);
      try (var inputStream = multipartFile.getInputStream()) {
        // Save file (overwrite if exists)
        Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
      }
      log.info("-------------- Starting to launch the Job to process the file");
      JobParameters jobParameters = new JobParametersBuilder()
          .addLong("time", System.currentTimeMillis())
          .addString(Constants.STRING_PATH, target.toString())
          .addString(Constants.IS_REPLACE_FILES, "true")
          .toJobParameters();

      jobLauncher.run(job, jobParameters);

      Map<String, String> response = new HashMap<>();
      response.put("File", target.toString());
      response.put("Status", "Recived");

      return ResponseEntity.ok(response);

    } catch (Exception e) {
      log.error("Error uploading the file {}", e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknow error.");
  }

}
