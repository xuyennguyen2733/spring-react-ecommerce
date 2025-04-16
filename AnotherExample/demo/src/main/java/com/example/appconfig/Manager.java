package com.example.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Manager {
  @Autowired
  private Employee employee;


  @Override
  public String toString() {
    return "Manager [toString()=" + employee.toString() + "]";
  }
}
