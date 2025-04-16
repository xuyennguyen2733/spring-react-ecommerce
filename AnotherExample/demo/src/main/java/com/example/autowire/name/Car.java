package com.example.autowire.name;

public class Car {

  Specification specification;

  public void setSpecification(Specification specification) {
    this.specification = specification;
  }

  public String getCarInfo() {
    return specification.toString();
  }
}
