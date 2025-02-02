package com.dkte.pizzashop.entity;

import java.util.Scanner;

public class Customer {

	private int cid;
	private String name;
	private String email;
	private String password;
	private String mobile;
	
	public Customer() {
		
	}
	
	public Customer(int cid, String name, String email, String password, String mobile) {
		this.cid = cid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void accept(Scanner sc) {
		
		System.out.println("Enter the name of customer = ");
		name=sc.next();
		System.out.println("Enter the email of customer = ");
		email=sc.next();
		System.out.println("Enter the password of customer = ");
		password=sc.next();
		System.out.println("Enter the mobile of customer = ");
		mobile=sc.next();
	}

	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", name=" + name + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + "]";
	}
	
}
