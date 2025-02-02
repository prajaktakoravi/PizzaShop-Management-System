package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entity.Pizza;
import com.dkte.pizzashop.util.DBUtil;

public class OrderDao implements AutoCloseable{
	
	private Connection connection;

	public OrderDao()throws SQLException{
		this.connection=DBUtil.getConnection();
	}
	
	public void placeOrder(int cid,int mid)throws SQLException{
		String sql = "INSERT INTO orders (cid, mid) " + "SELECT customer.cid, menu.mid " + "FROM customer JOIN menu "
				+ "ON customer.cid = ? AND menu.mid = ?";

		try (PreparedStatement insertstmt = connection.prepareCall(sql)) {
			insertstmt.setInt(1, cid);
			insertstmt.setInt(2, mid);
			insertstmt.executeUpdate();
		}	
	}
	
	@Override
	public void close() throws Exception {
		if (connection != null && !connection.isClosed())
			connection.close();		
	}

	public List<Pizza> orderHistory(int cid) throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		String sql = "SELECT orders.mid, menu.name, menu.price FROM orders JOIN menu on orders.mid = menu.mid where orders.cid=?";
		try (PreparedStatement selectstmt = connection.prepareCall(sql)) {
			selectstmt.setInt(1, cid);
			ResultSet rs = selectstmt.executeQuery();
			while (rs.next()) {
				Pizza pizza = new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(cid);
				pizzaList.add(pizza);
			}
		}
		return pizzaList;
	}

}
