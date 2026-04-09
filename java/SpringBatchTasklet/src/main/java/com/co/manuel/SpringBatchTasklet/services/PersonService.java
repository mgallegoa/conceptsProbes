package com.co.manuel.SpringBatchTasklet.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;

@Service
public class PersonService implements IPersonService {

  private PersonRepository personRepository;

  PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  @Transactional
  public void saveAll(List<Person> personList) {

    personRepository.saveAll(personList);
  }

}
