package com.co.manuel.SpringBatchTasklet.steps;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.co.manuel.SpringBatchTasklet.config.BatchConfiguration;
import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.helpers.CastHelper;
import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemWriterStep implements Tasklet {

  private PersonRepository personRepository;

  public ItemWriterStep(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init WRITER Step ------ ");

    List<Person> personList = CastHelper.castList(
        chunkContext.getStepContext().getJobExecutionContext().get(BatchConfiguration.KEY_PERSON_LIST), Person.class);

    personList.forEach(person -> {
      log.info(person.toString());
    });

    personRepository.saveAll(personList);

    log.info("------ Finished WRITER Step ------ ");
    return RepeatStatus.FINISHED;
  }

}
