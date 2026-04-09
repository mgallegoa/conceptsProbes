package com.co.manuel.SpringBatchTasklet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.co.manuel.SpringBatchTasklet.steps.ItemReaderStep;

@Configuration
public class BatchConfiguration {

  @Bean
  public ItemReaderStep itemReaderStep() {
    return new ItemReaderStep();
  }
}
