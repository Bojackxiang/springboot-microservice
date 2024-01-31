package com.alex.restfulwebservice.controller;

import com.alex.restfulwebservice.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
  @GetMapping(path = "/hello-world")
  public String HelloWorld(){
    return "hello world";
  }

  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean HelloWorldBean(){
    return new HelloWorldBean("Hello-world");
  }

}
