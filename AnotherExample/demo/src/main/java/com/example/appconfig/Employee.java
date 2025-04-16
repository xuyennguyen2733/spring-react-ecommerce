package com.example.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("employee")
public class Employee {
  @Value("1")
  private int employeeId;
  @Value("Joseph")
  private String firstName;

  public int getEmployeeId() {
    return employeeId;
  }
  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  @Override
  public String toString() {
    return "{Employee: employeeId='" + employeeId + "', firstName='" + firstName + "'}";
  }
}
