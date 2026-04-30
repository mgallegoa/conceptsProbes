package com.co.manuel.SpringBatchChunk.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.co.manuel.SpringBatchChunk.entities.Person;

@Configuration
@EnableBatchProcessing
// @Import(ApplicationConfig.class)
public class BatchConfig {

  @Bean
  public Step processFile(JobRepository jobRepository, PlatformTransactionManager transactionManager,
      ItemReader<Person> personReader,
      ItemWriter<Person> personWriter) {
    Step step = new StepBuilder("manuelStepReadFile", jobRepository)
        .<Person, Person>chunk(2, transactionManager)// .tasklet()
        .reader(personReader)
        .writer(personWriter)
        .build();

    return step;
  }

  @Bean
  public Job job(JobRepository jobRepository, Step processFile) {
    return new JobBuilder("manuelJobReadFileChunk", jobRepository)
        .start(processFile)
        .build();
  }

}
