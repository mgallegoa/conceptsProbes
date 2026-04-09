package com.co.manuel.SpringBatchTasklet.repositories;

import org.springframework.data.repository.CrudRepository;

import com.co.manuel.SpringBatchTasklet.entities.Person;

// @Repository not necessary because CrudRepository inject Bean
public interface PersonRepository extends CrudRepository<Person, Long> {

}
