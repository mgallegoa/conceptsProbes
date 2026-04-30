package com.co.manuel.SpringBatchChunk.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.co.manuel.SpringBatchChunk.entities.Person;

@Configuration
public class PersonItemProcessor {

  @Bean
  @StepScope
  public ItemProcessor<Person, Person> personProcessor() {
    return person -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      LocalDateTime localDate = LocalDateTime.now();
      person.setName(person.getName().toUpperCase());
      person.setCreateAt(formatter.format(localDate));
      return person;
    };
  }

}
