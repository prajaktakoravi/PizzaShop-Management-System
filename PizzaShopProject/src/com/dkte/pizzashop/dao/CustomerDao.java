package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkte.pizzashop.entity.Customer;
import com.dkte.pizzashop.util.DBUtil;

public class CustomerDao implements AutoCloseable {
	
	private Connection connection;

	public CustomerDao()throws SQLException{
		connection=DBUtil.getConnection();
	}
	
	public void insertCustomer(Customer customer)throws SQLException {
		String sql="INSERT INTO Customer(name,email,password,mobile)VALUES(?,?,?,?)";
		try(PreparedStatement insert=connection.prepareCall(sql)){
			insert.setString(1, customer.getName());
			insert.setString(2, customer.getEmail());
			insert.setString(3, customer.getPassword());
			insert.setString(4,customer.getMobile());
			insert.executeUpdate();
		}
	}

	public Customer getCustomer(String email,String password)throws SQLException{
		String sql="SELECT * FROM Customer WHERE email=? AND password=?";
		try(PreparedStatement select = connection.prepareStatement(sql)){
			select.setString(1, email);
			select.setString(2, password);
			ResultSet rs=select.executeQuery();
			if(rs.next()) {
				Customer customer=new Customer();
				customer.setEmail(rs.getString(1));
				customer.setPassword(rs.getString(2));
				return customer;
			}
		}
		return null;		
	}
	
	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}
		
}
