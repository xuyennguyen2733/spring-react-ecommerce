package com.ioc.coupling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCExample {
  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationIocLooseCouplingExample.xml");

    UserManager userManager = (UserManager) context.getBean("userManagerWithUserDataProvider");
    System.out.println(userManager.getUserInfo());

    UserManager userManagerWS = (UserManager) context.getBean("userManagerWithWebServiceDataProvider");
    System.out.println(userManagerWS.getUserInfo());

    UserManager userManagerND = (UserManager) context.getBean("userManagerWithNewDatabaseProvider");
    System.out.println(userManagerND.getUserInfo());

  }
}
