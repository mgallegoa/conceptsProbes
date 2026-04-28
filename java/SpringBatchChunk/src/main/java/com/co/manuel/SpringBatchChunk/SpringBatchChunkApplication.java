package com.co.manuel.SpringBatchChunk;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.TaskExecutorJobOperator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBatchChunkApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchChunkApplication.class, args);
  }

  @Bean
  CommandLineRunner init(Job job) {
    return args -> {
      JobParameters parameter = new JobParametersBuilder()
          .addString("name", "SpringChunk")
          .addString("inputFile", "classpath:persons.csv")
          .addLong("id", System.currentTimeMillis())
          .toJobParameters();

      JobOperator jobOperator = new TaskExecutorJobOperator();
      jobOperator.start(job, parameter);

    };
  }

}
