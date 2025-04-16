package com.ioc.coupling;

public class UserDatabaseProvider implements UserDataProvider{
  public String getUserDetails() {
    return "user database provider";
  }
}
