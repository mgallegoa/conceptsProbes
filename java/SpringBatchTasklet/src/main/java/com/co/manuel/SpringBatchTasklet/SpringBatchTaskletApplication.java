package com.co.manuel.SpringBatchTasklet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.co.manuel.SpringBatchTasklet.entities.Person;
import com.co.manuel.SpringBatchTasklet.repositories.PersonRepository;

@SpringBootApplication
public class SpringBatchTaskletApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchTaskletApplication.class, args);
  }

  // @Bean
  CommandLineRunner init(PersonRepository personRepository) {
    return (args) -> {

      Person person = new Person();
      person.setName("Manuel");
      person.setLastName("Arias");
      person.setAge(40);

      personRepository.save(person);

    };
  }

}
