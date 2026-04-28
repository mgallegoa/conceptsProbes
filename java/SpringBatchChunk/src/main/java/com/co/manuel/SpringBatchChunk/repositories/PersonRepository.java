package com.co.manuel.SpringBatchChunk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.manuel.SpringBatchChunk.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
