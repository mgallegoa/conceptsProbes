package com.co.manuel.SpringSecurityApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAutController {

  @GetMapping("hello")
  public String hello() {
    return "Hello from Manuel Security app, NOT secured endpoint.";
  }

  @GetMapping("hello-secured")
  public String helloSecure() {
    return "Hello from Manuel Security app, secured endpoint.";
  }
}
