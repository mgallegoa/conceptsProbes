package com.co.manuel.SpringBatchTasklet.steps;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ResourceLoader;

import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.helpers.Constants;
import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// This class should be defined like a bean in the config
public class ItemReaderStep implements Tasklet {

  // To import a file from resources folder
  private ResourceLoader resourceLoader;

  private PersonRepository personRepository;

  public ItemReaderStep(ResourceLoader resourceLoader, PersonRepository personRepository) {
    this.personRepository = personRepository;
    this.resourceLoader = resourceLoader;
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    log.info("------ Init READER Step ------ ");

    ExecutionContext executionContext = chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext();
    Object objFilePath = executionContext.get(Constants.FILE_PATH_TO_PROCESS);
    if (objFilePath == null) {
      log.info("------ Not context for the file path - Finished READER Step ------ ");
      return RepeatStatus.FINISHED;
    }
    String newPath = (String) objFilePath;

    log.info("------ Name for the file path ------ " + newPath);
    // The classpath direct to the resource folder
    String filePath = "file:" + newPath;
    Reader reader = new FileReader(resourceLoader.getResource(filePath).getFile());

    // Using OpenCSV library, added to pom.xml
    CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
    CSVReader csvReader = new CSVReaderBuilder(reader)
        // to specify the separator
        .withCSVParser(parser)
        // to specify the number of lines to skip from the start of the file
        .withSkipLines(1).build();

    List<Person> personList = new ArrayList<>();
    String[] currentLine;
    String processId = UUID.randomUUID().toString();

    while ((currentLine = csvReader.readNext()) != null) {
      Person person = new Person();
      person.setName(currentLine[0]);
      person.setLastName(currentLine[1]);
      person.setAge(Integer.parseInt(currentLine[2]));
      person.setProcessId(processId);

      personList.add(person);

    }
    csvReader.close();
    reader.close();

    log.info("------ Saving in bd ------ ");
    personRepository.saveAll(personList);

    /*
     * Example of bad practice, save business data in the context
     * chunkContext.getStepContext()
     * .getStepExecution()
     * .getJobExecution()
     * .getExecutionContext()
     * .put(BatchConfiguration.KEY_PERSON_LIST, personList);
     */
    executionContext.put(Constants.PROCESS_ID, processId);
    log.info("------ Finished READER Step ------ ");
    return RepeatStatus.FINISHED;
  }

}
