package com.co.manuel.SpringSecurityApp;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.co.manuel.SpringSecurityApp.entities.PermissionEntity;
import com.co.manuel.SpringSecurityApp.entities.RoleEntity;
import com.co.manuel.SpringSecurityApp.entities.RoleEnum;
import com.co.manuel.SpringSecurityApp.entities.UserEntity;
import com.co.manuel.SpringSecurityApp.repositories.UserRepository;

// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class SpringSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApplication.class, args);
  }

  @Bean
  CommandLineRunner init(UserRepository userRepository) {
    return args -> {
      /* Create permissions */
      PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
      PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();
      PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();
      PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();
      PermissionEntity refactorPermission = PermissionEntity.builder().name("REFACTOR").build();

      /* Create roles */
      RoleEntity adminRole = RoleEntity.builder().roleEnum(RoleEnum.ADMIN)
          .permissionList(Set.of(readPermission, createPermission, updatePermission, deletePermission)).build();
      RoleEntity userRole = RoleEntity.builder().roleEnum(RoleEnum.USER)
          .permissionList(Set.of(readPermission, createPermission)).build();
      RoleEntity invitedRole = RoleEntity.builder().roleEnum(RoleEnum.INVITED)
          .permissionList(Set.of(readPermission)).build();
      RoleEntity developRole = RoleEntity.builder().roleEnum(RoleEnum.DEVELOPER)
          .permissionList(
              Set.of(readPermission, createPermission, updatePermission, deletePermission, refactorPermission))
          .build();

      /* Create the users */
      UserEntity userManuel = UserEntity.builder().username("manuel").password("123").isEnable(true)
          .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(adminRole)).build();

      UserEntity userJoe = UserEntity.builder().username("joe").password("321").isEnable(true)
          .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(userRole)).build();
      UserEntity userFernando = UserEntity.builder().username("fernando").password("321").isEnable(true)
          .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(invitedRole))
          .build();
      UserEntity userArias = UserEntity.builder().username("arias").password("321").isEnable(true)
          .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(developRole))
          .build();

      /* Save Users */
      userRepository.saveAll(List.of(userManuel, userJoe, userFernando, userArias));
      userRepository.flush();
    };
  }

}
