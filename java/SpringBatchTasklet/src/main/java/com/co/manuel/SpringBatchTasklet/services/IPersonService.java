package com.co.manuel.SpringBatchTasklet.services;

import java.util.List;

import com.co.manuel.SpringBatchTasklet.entities.Person;

public interface IPersonService {

  public void saveAll(List<Person> personList);
}
