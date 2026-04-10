package com.co.manuel.SpringBatchTasklet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;
import com.co.manuel.SpringBatchTasklet.steps.ItemDescompressStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemProcessorStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemReaderStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemWriterStep;

@Configuration
public class BatchConfiguration {

  public static final String KEY_PERSON_LIST = "personList";

  @Bean
  public ItemDescompressStep itemDescompressStep() {
    return new ItemDescompressStep();
  }

  @Bean
  public ItemReaderStep itemReaderStep() {
    return new ItemReaderStep();
  }

  @Bean
  public ItemProcessorStep itemProcessorStep() {
    return new ItemProcessorStep();
  }

  @Bean
  public ItemWriterStep itemWriterStep(PersonRepository personRepository) {
    return new ItemWriterStep(personRepository);
  }
}
