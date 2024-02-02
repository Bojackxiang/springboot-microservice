package com.alex.restfulwebservice.customCommandRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomRunner2 implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("CustomRunner1.run");
  }
}
