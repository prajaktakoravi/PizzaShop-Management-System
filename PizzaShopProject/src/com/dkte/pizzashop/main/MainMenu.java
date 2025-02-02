package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entity.Customer;
import com.dkte.pizzashop.submain.SubMenu;

public class MainMenu {
	public static int menu(Scanner sc) {
		System.out.println("**********Welcome**********");
		System.out.println("0.Exit");
		System.out.println("1.Login");
		System.out.println("2.Register");
		System.out.println("Enter the choice = ");
		int choice = -1;
		try {
			choice=sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid input. Please enter a number.");
			sc.nextInt();
		}
		System.out.println("***************************");
		return choice;
	}
	
	private static void registerCustomer(Scanner sc) {
		Customer customer=new Customer();
		customer.accept(sc);
		try(CustomerDao customerDao=new CustomerDao()){
			customerDao.insertCustomer(customer);
			System.out.println("Registration successfully");
		}catch(SQLException e) {
			System.out.println("Error: Unable to register. Please try again later.");
			e.printStackTrace();
		}
	}
	
	private static Customer loginCustomer(Scanner sc) {
		try (CustomerDao customerDao=new CustomerDao()){
			System.out.println("Enter the Email = ");
			String email=sc.next();
			System.out.println("Enter the password = ");
			String password=sc.next();
			
			Customer customer=customerDao.getCustomer(email, password);
			if (customer != null) { 
				System.out.println("Login successful...!"); 
				return customer; 
			} else { 
			System.out.println("Invalid email or password. Please try again."); 
			} 
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		
		} 
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int choice;
		while((choice=menu(sc))!=0) {
			switch(choice) {
			case 1:
				System.out.println("Login Cliked...");
				Customer customer=loginCustomer(sc);
				if(customer !=null)
					SubMenu.subMenu(sc,customer);
				else
					System.out.println("Invalid candidate...");
				
				break;
			case 2:
				System.out.println("Registration Cliked...");
				registerCustomer(sc);
				break;
			default:System.out.println("Wrong choice...");
			}
		}
		System.out.println("Thank you for Using this App...");
		sc.close();
	}

}
