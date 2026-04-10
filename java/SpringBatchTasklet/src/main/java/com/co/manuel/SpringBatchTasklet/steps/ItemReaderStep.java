package com.co.manuel.SpringBatchTasklet.steps;

import static java.lang.System.lineSeparator;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

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

    // The classpath direct to the resource folder
    String filePath = "classpath:files" + lineSeparator() + "destination"
        + lineSeparator() + "persons.csv";
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

    while ((currentLine = csvReader.readNext()) != null) {
      Person person = new Person();
      person.setName(currentLine[0]);
      person.setLastName(currentLine[1]);
      person.setAge(Integer.parseInt(currentLine[2]));

      personList.add(person);

    }
    csvReader.close();
    reader.close();

    chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext()
        .put("personList", personList);

    log.info("------ Finished READER Step ------ ");
    return RepeatStatus.FINISHED;
  }

}
