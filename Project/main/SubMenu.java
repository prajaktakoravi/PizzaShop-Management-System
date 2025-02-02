package com.dkte.pizzashop.main;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Order;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.dao.OrderDao;
public class SubMenu {
	private static int menu(Scanner sc)
	{
		System.out.println("0.Logout");
		System.out.println("1.Pizza Menu");
		System.out.println("2.Order pizza");
		System.out.println("3.Order history");
		System.out.println("**************************");
		System.out.println("Enter your choice:");
		int ch=sc.nextInt();
		return ch;
	}

	public static void subMenu(Scanner sc,Customer customer) {
		System.out.println("Welcome "+customer.getName());
		int ch;
		while((ch=menu(sc))!=0)
		{
			switch(ch)
			{
			case 0:
				System.out.println("Logout");
				break;
			case 1:
				displayMenu();
				break;
			case 2:
				OrderPizza(sc, customer.getCid());
				break;
			case 3:
				System.out.println("Order history)");
				getAllOrders(customer.getCid());
				break;
			case 4:
				System.out.println("Wrong choice...)");
				break;
			}
		}
		
	}
	private static void getAllOrders(int cid) {
		try (OrderDao OrderDao = new OrderDao()) {
			List<Pizza> pizzaList = OrderDao.getAllOrders(cid);
			pizzaList.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private static void OrderPizza(Scanner sc, int cid) {
		System.out.print("Enter the id of the pizza you want to order: ");
		int mid = sc.nextInt();
		try (OrderDao orderDao = new OrderDao()) {
			orderDao.placeOrder(cid, mid);
			System.out.println("Pizza ordered successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void displayMenu()
	{
		try(PizzaDao pizzaDao=new PizzaDao()){
				List<Pizza>pizzaList=pizzaDao.getAllPizza();
				pizzaList.forEach(p->System.out.println(p));
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
