package com.co.manuel.SpringSecurityApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.co.manuel.SpringSecurityApp.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  public Optional<UserEntity> findUserEntityByUsername(String username);

  /* The two way to implement the queries */
  @Query("SELECT u FROM UserEntity u  WHERE u.username = :username")
  public Optional<UserEntity> findUser(String username);

}
