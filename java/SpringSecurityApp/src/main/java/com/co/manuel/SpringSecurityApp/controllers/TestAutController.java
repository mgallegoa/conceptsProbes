package com.co.manuel.SpringSecurityApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
}
