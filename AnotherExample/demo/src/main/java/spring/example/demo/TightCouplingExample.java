package spring.example.demo;

public class TightCouplingExample {
  

  public static void main(String args[]) {
    UserManagement userManagement = new UserManagement();
    System.out.println(userManagement.getUserInfo());
  }
}
