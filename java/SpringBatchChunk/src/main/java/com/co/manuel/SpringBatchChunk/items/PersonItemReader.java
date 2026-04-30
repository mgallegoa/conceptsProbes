package com.co.manuel.SpringBatchChunk.items;

import java.nio.charset.StandardCharsets;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.co.manuel.SpringBatchChunk.entities.Person;

@Configuration
public class PersonItemReader {

  private ResourceLoader resourceLoader;

  public PersonItemReader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @Bean
  public ItemReader<Person> personReader() {
    Resource resource = resourceLoader.getResource("classpath:persons.csv");
    FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
    reader.setResource(resource);
    reader.setLinesToSkip(1);
    reader.setEncoding(StandardCharsets.UTF_8.name());
    reader.setLineMapper(getLineMapper());

    return reader;
  }

  public LineMapper<Person> getLineMapper() {
    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

    String[] columns = new String[] { "name", "lastName", "age" };
    int[] indexFields = new int[] { 0, 1, 2 };

    lineTokenizer.setNames(columns);
    lineTokenizer.setIncludedFields(indexFields);
    lineTokenizer.setDelimiter(",");

    BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Person.class);

    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

}
