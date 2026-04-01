package com.co.manuel.SpringSecurityApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// To use @PreAuthorize need the annotation @EnableMethodSecurity in the configuration
@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAutController {

  @GetMapping("hello")
  @PreAuthorize("permitAll")
  public String hello() {
    return "Hello from Manuel Security app, NOT secured endpoint.";
  }

  @GetMapping("hello-secured")
  @PreAuthorize("hasAuthority('READ')")
  public String helloSecure() {
    return "Hello from Manuel Security app, secured endpoint.";
  }

  @GetMapping("hello-secured2")
  @PreAuthorize("hasAuthority('CREATE')")
  public String helloSecure2() {
    return "Hello from Manuel Security app, secured endpoint 2.";
  }

  @GetMapping("get")
  @PreAuthorize("hasAuthority('READ')")
  public String helloGet() {
    return "Hello - GET";
  }

  @PostMapping("/post")
  @PreAuthorize("hasAuthority('CREATE') or hasAuthority('UPDATE')")
  public String helloPost() {
    return "Hello - POST";
  }

  @PutMapping("/put")
  public String helloPut() {
    return "Hello - PUT";
  }

  @DeleteMapping("/delete")
  public String helloDelete() {
    return "Hello - DELETE";
  }

  @PatchMapping("/patch")
  @PreAuthorize("hasAuthority('REFACTOR')")
  public String helloPatch() {
    return "Hello - PATCH";
  }
}
