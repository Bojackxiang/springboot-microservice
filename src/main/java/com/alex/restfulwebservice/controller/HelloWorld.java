package com.alex.restfulwebservice.controller;

import com.alex.restfulwebservice.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
  @GetMapping(path = "/hello-world")
  public String HelloWorld() {
    return "hello world";
  }

  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean HelloWorldBean() {
    return new HelloWorldBean("Hello-world");
  }

  @GetMapping(path = "/hello-world/path-variable/{name}")
  public HelloWorldBean HelloWorldPathVariable(@PathVariable String name) {
    return new HelloWorldBean(name);
  }

}
