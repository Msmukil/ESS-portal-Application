package com.te.essportalapplication;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {

  static Scanner scan = new Scanner(System.in);

  static void register() {
    Employee_info employee_Info = new Employee_info();
    System.out.println("enter the name");
    employee_Info.setEmployee_Name(scan.next());

    System.out.println("enter employee type \n press 1 for manager \n press 2 for employee");
    int x = scan.nextInt();
    if (x == 1) {
      employee_Info.setEmployee_Type("manager");
    } else {
      employee_Info.setEmployee_Type("Employee");
    }

    System.out.println("enter Email");
    employee_Info.setEmail(scan.next());

    System.out.println("enter password");
    employee_Info.setPassword(scan.next());

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Essportal");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    transaction.begin();
    manager.persist(employee_Info);
    transaction.commit();

  }

  static void login() {
    System.out.println("Enter your Id");
    int emp_id = scan.nextInt();
    System.out.println("Enter your Password");
    String emp_password = scan.next();

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Essportal");
    EntityManager manager = factory.createEntityManager();
    try {
      Employee_info info = manager.find(Employee_info.class, emp_id);
      int id = info.getEmployee_ID();
      String pass = info.getPassword();
      if (emp_password.equals(pass) && emp_id == id) {
        System.out.println("Successfully Logged in");
        if (info.getEmployee_Type().contentEquals("manager")) {
          Manager manage = new Manager();
          manage.manager_screen(info);
        } else {
          Employee employee = new Employee();
          employee.employee_screen(info);
        }
      } else if (emp_id == id) {
        System.out.println("Wrong password");
      } else {
        System.out.println("User Record Not found");
      }
    } catch (NullPointerException e) {
      System.out.println("Invalid User\nTry to register");
    }
  }

  public static void main(String[] args) {
    boolean y = true;
    while (y) {

      System.out.println("Employee Portal");
      System.out.println("•  Press 1 to login\n•  Press 2 to register");
      int z = scan.nextInt();
      switch (z) {
      case 1: {
        login();
        break;
      }
      case 2: {
        register();
        break;
      }
      default: {
        System.out.println("Your choice is invalid");
        y = false;
        break;
      }
      }

    }
  }
}

