package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.utils.DBUtil;

public class CustomerDao implements AutoCloseable {

	private Connection connection;

	public CustomerDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public void insertStatement(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer(name,email,password,mobile) VALUES(?,?,?,?)";
		try (PreparedStatement insertStatement = connection.prepareCall(sql)) {
			insertStatement.setString(1, customer.getName());
			insertStatement.setString(2, customer.getEmail());
			insertStatement.setString(3, customer.getPassword());
			insertStatement.setString(4, customer.getMobile());
			insertStatement.executeUpdate();
		}
	}


	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

	public Customer getCustomer(String email, String password) throws SQLException {
		String sql = "SELECT * FROM Customer where email=? AND password=?";
		try (PreparedStatement selectStmt = connection.prepareStatement(sql)) {
			selectStmt.setString(1, email);
			selectStmt.setString(2, password);

			ResultSet rs = selectStmt.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				customer.setCid(rs.getInt(1));
				customer.setEmail(email);
				customer.setPassword(password);
				customer.setName(rs.getString(2));
				customer.setMobile(rs.getString(5));
				return customer;

			}
			return null;
		}
	}
}
