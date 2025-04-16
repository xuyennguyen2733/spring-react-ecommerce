package car.example.constructor;

public class Specification {
  private String make;
  private String model;

  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return "Specification{" +
            "make='" + make + "', " + 
            "model='" + model + "'" +
            "}";
  }
}
