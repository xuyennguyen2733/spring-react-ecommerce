package com.example.autowire.name;

public class Specification {
  private String make;
  private String model;

  public void setModel(String model) {
    this.model = model;
  }

  public String getModel() {
    return model;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getMake() {
    return make;
  }

  @Override
  public String toString() {
    return "Specification{" +
            "make='" + make + "', " + 
            "model='" + model + "'" +
            "}";
  }
}
