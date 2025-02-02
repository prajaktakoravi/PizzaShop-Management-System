package com.dkte.pizzashop.submain;

import java.sql.SQLException;

import java.util.Scanner;
import java.util.List;

import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entity.Customer;
import com.dkte.pizzashop.entity.Pizza;

public class SubMenu {
	public static int menu(Scanner sc) {
		System.out.println("***Welcome To PizzaShop***");
		System.out.println("0.Logout");
		System.out.println("1.Pizza Menu");
		System.out.println("2.Order Pizza");
		System.out.println("3.Order History");
		System.out.println("Enter the choice = ");
		int choice=sc.nextInt();
		System.out.println("**************************");
		return choice;
	}
	
	private static void displayNames() throws Exception { 
		try (PizzaDao pizzaDao = new PizzaDao()) { 
			List<Pizza> pizzaList = pizzaDao.getAllPizza(); 
			pizzaList.forEach(p -> System.out.println(p)); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
			} 
		}
	
	private static void orderPizza(Scanner sc, int cid){
		System.out.println("Enter the mid = ");
        int mid = sc.nextInt();
		try (OrderDao orderDao = new OrderDao()) {     
	        orderDao.placeOrder(cid, mid);
	        System.out.println("Order successfully placed.");
	    } catch (Exception e) {
	        System.out.println("Error: Unable to place order. Please try again later.");
	        e.printStackTrace();
	    }
	}
	
	public static void getorderHistory(int cid) {
		List<Pizza> pizzaList;
		try (OrderDao orderDao = new OrderDao()) {
			pizzaList = orderDao.orderHistory(cid); // Corrected method call
			if (pizzaList.isEmpty()) {
				System.out.println("No orders found for Customer ID: " + cid);
			} else {
				System.out.println("Order History for Customer ID: " + cid);
				pizzaList.forEach(p -> {
					System.out.println("Pizza ID: " + p.getMid());
					System.out.println("Name: " + p.getName());
					System.out.println("Description: " + p.getDescription());
					System.out.println("Price: $" + p.getPrice());
					System.out.println("---------------------------");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void subMenu(Scanner sc,Customer customer) throws Exception{
		int choice;
		while((choice=menu(sc))!=0) {
			switch(choice) {
			case 1:
				System.out.println("Pizza Menu...");
				displayNames();
				break;
			case 2:
				System.out.println("Order pizza...");
				orderPizza(sc, customer.getCid());
				break;
			case 3:
				System.out.println("Order History...");
				getorderHistory(customer.getCid());
				break;
			default:System.out.println("Wrong choice...");
			}
		}
		System.out.println("Logout Successfully...");

	}

}
