package com.co.manuel.SpringBatchTasklet.steps;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.co.manuel.SpringBatchTasklet.config.BatchConfiguration;
import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.helpers.CastHelper;

public class ItemProcessorStep implements Tasklet {

  private static Logger log = LogManager.getLogger(ItemProcessorStep.class);

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init PROCESSOR Step ------ ");

    List<Person> personList = CastHelper.castList(chunkContext.getStepContext()
        .getJobExecutionContext()
        .get(BatchConfiguration.KEY_PERSON_LIST), Person.class);

    List<Person> personListProcessed = personList.stream().map(person -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      person.setCreateDate(formatter.format(LocalTime.now()));
      return person;
    }).toList();

    chunkContext.getStepContext().getJobExecutionContext().put(BatchConfiguration.KEY_PERSON_LIST, personListProcessed);

    log.info("------ Finished PROCESSOR Step ------ ");
    return RepeatStatus.FINISHED;
  }

}
