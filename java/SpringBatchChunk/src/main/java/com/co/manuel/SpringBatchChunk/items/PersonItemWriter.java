package com.co.manuel.SpringBatchChunk.items;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.co.manuel.SpringBatchChunk.entities.Person;
import com.co.manuel.SpringBatchChunk.repositories.PersonRepository;

@Configuration
public class PersonItemWriter {

  private PersonRepository personRepository;

  public PersonItemWriter(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Bean
  @StepScope
  public ItemWriter<Person> personWriter() {
    return items -> {
      personRepository.saveAll(items);
    };
  }
}
