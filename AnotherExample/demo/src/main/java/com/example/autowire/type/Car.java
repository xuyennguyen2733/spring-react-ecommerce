package com.example.autowire.type;

public class Car {

  Specification specification;

  public void setSpecification(Object specification) {
    this.specification = (Specification)specification;
  }

  public void setSpec(Specification spec) {
    this.specification = spec;
  }

  public String getCarInfo() {
    return specification.toString();
  }
}
