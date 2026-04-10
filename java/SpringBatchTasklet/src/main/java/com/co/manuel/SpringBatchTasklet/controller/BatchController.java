package com.co.manuel.SpringBatchTasklet.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BatchController {

  @PostMapping("/uploadFile")
  public ResponseEntity<?> receiveFile(MultipartFile multipartFile) {
    String fileName = multipartFile.getOriginalFilename();

    try {
      Path path = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "files"
          + File.separator + fileName);

      Files.createDirectories(path.getParent());
      Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

      Map<String, String> response = new HashMap<>();
      response.put("File", fileName);
      response.put("Status", "Recived");

      return ResponseEntity.ok(response);

    } catch (Exception e) {
      log.error("Error uploading the file {}", e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknow error.");
  }

}
