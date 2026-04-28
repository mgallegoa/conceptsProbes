package com.co.manuel.SpringBatchChunk.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.co.manuel.SpringBatchChunk.entities.Person;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Bean
  public Step processFile(JobRepository jobRepository, ItemReader<Person> personReader,
      ItemWriter<Person> personWriter) {
    Step step = new StepBuilder("manuelStepReadFile", jobRepository)
        .<Person, Person>chunk(2)// .tasklet()
        .reader(personReader)
        .writer(personWriter)
        .build();

    return step;
  }

  @Bean
  public Job job(JobRepository jobRepository, ItemReader<Person> personReader, ItemWriter<Person> personWriter) {
    return new JobBuilder("manuelJobReadFileChunk", jobRepository)
        .start(processFile(jobRepository, personReader, personWriter))
        .build();
  }

}
