package com.xuyen.firstspring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class HelloController {

  @GetMapping("/hello/{name}")
  public HelloResponse helloParam(@PathVariable String name) {
    return new HelloResponse("Hello, " + name + "!");
  }

  @GetMapping("/hello")
  public HelloResponse hello() {
    return new HelloResponse("Hello World!");
  }

  @PostMapping("/hello")
  public HelloResponse helloPost(@RequestBody String name) {
      //TODO: process POST request
      
      return new HelloResponse("Hello " + name + "!");
  }
  

}
