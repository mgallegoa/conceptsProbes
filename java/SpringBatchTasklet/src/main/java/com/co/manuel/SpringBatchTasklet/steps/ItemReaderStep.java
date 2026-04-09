package com.co.manuel.SpringBatchTasklet.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// This class should be defined like a bean in the config
public class ItemReaderStep implements Tasklet {

  // To import a file from resources folder
  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init READER Step ------ ");
    return null;
  }

}
