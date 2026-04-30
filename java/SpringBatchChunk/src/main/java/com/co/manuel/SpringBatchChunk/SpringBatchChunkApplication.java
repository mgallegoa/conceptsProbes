package com.co.manuel.SpringBatchChunk;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchChunkApplication {

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchChunkApplication.class, args);
  }

  @Bean
  CommandLineRunner init() {
    return args -> {
      JobParameters parameter = new JobParametersBuilder()
          .addString("name", "SpringChunk")
          .addString("inputFile", "classpath:persons.csv")
          .addLong("id", System.currentTimeMillis())
          .toJobParameters();
      System.out.println("********************************************");
      System.out.println("************** RUNNING JOB ***************");
      System.out.println("********************************************");

      jobLauncher.run(job, parameter);

    };
  }

  /*
   * Configuration for spring boot Version 6
   * 
   * @Bean
   * CommandLineRunner init(Job job) {
   * return args -> {
   * JobParameters parameter = new JobParametersBuilder()
   * .addString("name", "SpringChunk")
   * .addString("inputFile", "classpath:persons.csv")
   * .addLong("id", System.currentTimeMillis())
   * .toJobParameters();
   * System.out.println("********************************************");
   * System.out.println("************** RUNNING JOB ***************");
   * System.out.println("********************************************");
   * 
   * JobOperator jobOperator = new TaskExecutorJobOperator();
   * jobOperator.start(job, parameter);
   * 
   * };
   * }
   */

}
