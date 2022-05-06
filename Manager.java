package com.te.essportalapplication;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Manager {
	static Scanner sc = new Scanner(System.in);

	void viewRequests() {
		try {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Essportal");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from Employee_Leave");
		List<Employee_leave> list = query.getResultList();
		for (Employee_leave i : list) {
			System.out.println("ID : "+i.getEmployee_ID()+"    Date : "+i.getLeave_Date()+"    Status : "+ i.getLeave_Status());
		}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	void statusChangeRequests() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Essportal");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		System.out.println("Enter the ID to change Status:");
		int id = sc.nextInt();
		Employee_leave leave = manager.find(Employee_leave.class, id);
		System.out.println("Enter Status: \n•	Press 1 for Approved\n•	Press 2 for Rejected\n");
		int sts = sc.nextInt();
		switch(sts) {
		case 1:{
			leave.setLeave_Status("Approved");
			break;
		}
		case 2:{
			leave.setLeave_Status("Rejected");
			break;
		}
		default :{
			leave.setLeave_Status("Pending");
			break;
		}
		}
		transaction.begin();
		manager.persist(leave);
		transaction.commit();
		System.out.println("\nEmployee Status Updated Successfully\n");
		
		
	}

	void manager_screen(Employee_info info) {
		boolean status = true;
		while (status) {
			System.out.println("Hello Manager " + info.getEmployee_Name());
			System.out.println("	Press 1 to show all leave requests\n"
					+ "	Press 2 to Approve or reject leave requests\n" + "	Press 3 to go back to Main menu\n");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				viewRequests();
				break;
			}
			case 2: {
				statusChangeRequests();
				break;
			}
			default: {
				System.out.println("please enter a correct option");
				status = false;
				break;
			}
			}
		}
	}

}
