package com.co.manuel.SpringBatchTasklet.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;

import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;
import com.co.manuel.SpringBatchTasklet.steps.ItemDescompressStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemProcessorStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemReaderStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemWriterStep;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ResourceLoader resourceLoader;

  @Bean
  // Just enable the object with spring batch, optimization
  @JobScope
  public ItemDescompressStep itemDescompressStep() {
    return new ItemDescompressStep();
  }

  @Bean
  @JobScope
  public ItemReaderStep itemReaderStep() {
    return new ItemReaderStep(resourceLoader, personRepository);
  }

  @Bean
  @JobScope
  public ItemProcessorStep itemProcessorStep() {
    return new ItemProcessorStep(personRepository);
  }

  @Bean
  @JobScope
  public ItemWriterStep itemWriterStep(PersonRepository personRepository) {
    return new ItemWriterStep(personRepository);
  }

  /* Define the steps ---> */
  @Bean
  public Step decompressFileStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("itemDecompressFileStep", jobRepository)
        .tasklet(itemDescompressStep(), transactionManager)
        .build();
  }

  @Bean
  public Step readFileStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("itemReaderFileStep", jobRepository)
        .tasklet(itemReaderStep(), transactionManager)
        .build();
  }

  @Bean
  public Step processDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("itemReaderFileStep", jobRepository)
        .tasklet(itemProcessorStep(), transactionManager)
        .build();
  }

  @Bean
  public Step writeDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("itemReaderFileStep", jobRepository)
        .tasklet(itemWriterStep(personRepository), transactionManager)
        .build();
  }

  /* Define the job ---> */
  @Bean
  public Job jobReadFileAndSaveStep(JobRepository jobRepository, Step decompressFileStep, Step readFileStep,
      Step processDataStep, Step writeDataStep) {
    return new JobBuilder("decompressFileStep", jobRepository)
        .start(decompressFileStep)
        .next(readFileStep)
        .next(processDataStep)
        .next(writeDataStep)
        .build();

  }
}
