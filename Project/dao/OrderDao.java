package com.dkte.pizzashop.dao;

import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class OrderDao implements AutoCloseable {
	private Connection connection;

	public OrderDao() throws SQLException {
		connection = DBUtil.getConnection();
	}
	@Override
	public void close() throws Exception {
		if (connection != null)
			connection.close();
		
	}
	public void placeOrder(int cid,int mid) {
		String sql = "INSERT INTO orders(cid,mid) VALUES(?,?)";
		try (PreparedStatement insertStatement = connection.prepareCall(sql)) {
			insertStatement.setInt(1, cid);
		    insertStatement.setInt(2, mid);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Pizza> getAllOrders(int cid)throws SQLException {
		List<Pizza>pizzaList=new ArrayList<Pizza>();
		String sql="SELECT m.* FROM menu m INNER JOIN orders o ON m.mid=o.mid WHERE o.cid=? ";
		try (PreparedStatement selectStatement = connection.prepareCall(sql)) {
			selectStatement.setInt(1, cid);
			ResultSet rs=selectStatement.executeQuery();
			
			while(rs.next())
			{
				Pizza pizza=new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				pizzaList.add(pizza);
			}
	}
		return pizzaList;
	}

	
	
}
