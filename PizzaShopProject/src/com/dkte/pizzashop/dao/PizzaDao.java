package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entity.Pizza;
import com.dkte.pizzashop.util.DBUtil;

public class PizzaDao implements AutoCloseable {
	
	private Connection connection;

	public PizzaDao()throws SQLException{
		connection=DBUtil.getConnection();
	}
	
	public List<Pizza>getAllPizza()throws SQLException{
		List<Pizza> pizzalist=new ArrayList<Pizza>();
		String sql="SELECT * FROM menu";
		try(PreparedStatement select = connection.prepareCall(sql)){
			ResultSet rs=select.executeQuery();
			while(rs.next()) {
				Pizza pizza =new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				pizzalist.add(pizza);				
			}
		}
		return pizzalist;
	}

	@Override
	public void close() throws Exception {
		if (connection != null)
			connection.close();		
	}

}
