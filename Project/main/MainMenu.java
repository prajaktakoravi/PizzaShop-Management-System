package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Customer;

public class MainMenu {

	private static int menu(Scanner sc)
	{
		System.out.println("**Welcome to pizza store**");
		System.out.println("0.Exit");
		System.out.println("1.Login");
		System.out.println("2.Register");
		System.out.println("**************************");
		System.out.println("Enter your choice:");
		int ch=sc.nextInt();
		sc.nextLine();
		return ch;
	}
	private static Customer loginCustomer(Scanner sc) throws SQLException {
		try(CustomerDao customerDao=new CustomerDao()){
			System.out.println("Enter email");
			String email=sc.next();
			System.out.println("Enter password");
			String password=sc.next();
			return customerDao.getCustomer(email,password);
		} 
	}
	private static void registerCustomer(Scanner sc) throws SQLException {
		Customer customer=new Customer();
		customer.acceptCustomer(sc);
		try(CustomerDao customerDao=new CustomerDao()){
			customerDao.insertStatement(customer);
		} 
	}
	
	public static void main(String[] args) throws SQLException {
	Scanner sc=new Scanner(System.in);
	int ch;
	while((ch=menu(sc))!=0)
	{
		switch(ch) {
		case 1:
			Customer customer=loginCustomer(sc);
			if(customer!=null)
				SubMenu.subMenu(sc, customer);
			else
				System.out.println("Invalid cardentilas");
			break;
		case 2:
				registerCustomer(sc);
			break;
		default:
			System.out.println("Thank you for visiting");
			break;
		}
		
	}
	}

}
