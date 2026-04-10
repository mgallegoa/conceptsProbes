package com.co.manuel.SpringBatchTasklet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.co.manuel.SpringBatchTasklet.steps.ItemProcessorStep;
import com.co.manuel.SpringBatchTasklet.steps.ItemReaderStep;

@Configuration
public class BatchConfiguration {

  public static final String KEY_PERSON_LIST = "personList";

  @Bean
  public ItemReaderStep itemReaderStep() {
    return new ItemReaderStep();
  }

  @Bean
  public ItemProcessorStep itemProcessorStep() {
    return new ItemProcessorStep();
  }
}
