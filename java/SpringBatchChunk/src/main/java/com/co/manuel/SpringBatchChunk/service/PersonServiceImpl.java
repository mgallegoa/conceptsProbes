package com.co.manuel.SpringBatchChunk.service;

import java.util.List;

import com.co.manuel.SpringBatchChunk.entities.Person;
import com.co.manuel.SpringBatchChunk.repositories.PersonRepository;

public class PersonServiceImpl implements PersonService {

  private PersonRepository personRepository;

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Iterable<Person> saveAll(List<Person> listPersons) {
    return personRepository.saveAll(listPersons);
  }

}
