package com.co.manuel.SpringBatchTasklet.steps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.helpers.Constants;
import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;

public class ItemProcessorStep implements Tasklet {

  private static Logger log = LogManager.getLogger(ItemProcessorStep.class);

  private PersonRepository personRepository;

  public ItemProcessorStep(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init PROCESSOR Step ------ ");

    /*
     * Example of bad practice, save business data in context
     * List<Person> personList = CastHelper.castList(chunkContext.getStepContext()
     * .getJobExecutionContext()
     * .get(BatchConfiguration.KEY_PERSON_LIST), Person.class);
     */
    ExecutionContext executionContext = chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext();
    Object objProcessId = executionContext.get(Constants.PROCESS_ID);
    if (objProcessId == null) {
      log.info("------ Not context for process id - Finished PROCESSOR Step ------ ");
      return RepeatStatus.FINISHED;
    }

    List<Person> personList = personRepository.findByProcessId(objProcessId.toString());
    List<Person> personListProcessed = personList.stream().map(person -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      person.setCreateDate(LocalDateTime.now().format(formatter));
      return person;
    }).toList();

    personRepository.saveAll(personListProcessed);
    log.info("------ Finished PROCESSOR Step ------ ");
    return RepeatStatus.FINISHED;
  }

}
