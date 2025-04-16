package spring.example.demo;

public class UserManagement {
  private UserDatabase userDatabase = new UserDatabase();

  public String getUserInfo() {
    return userDatabase.getUserDetails();
  }
}
