package com.co.manuel.SpringBatchTasklet.steps;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.helpers.Constants;
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

    ExecutionContext executionContext = chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext();
    Object objProcessId = executionContext.get(Constants.PROCESS_ID);
    if (objProcessId == null) {
      log.info("------ Not context for process id - Finished WRITER Step ------ ");
      return RepeatStatus.FINISHED;
    }

    List<Person> personList = personRepository.findByProcessId(objProcessId.toString());
    // Print using Method Reference
    personList.forEach(person -> log.info(person.getName() + " " + person.getLastName() + " -> " + person.getAge()));

    log.info("------ Finished WRITER Step ------ ");
    return RepeatStatus.FINISHED;
  }

}
