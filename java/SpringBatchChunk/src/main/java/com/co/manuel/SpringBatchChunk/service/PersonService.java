package com.co.manuel.SpringBatchChunk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.co.manuel.SpringBatchChunk.entities.Person;

@Service
public interface PersonService {

  Iterable<Person> saveAll(List<Person> listPersons);
}
