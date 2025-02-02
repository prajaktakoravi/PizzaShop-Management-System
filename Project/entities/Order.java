package com.dkte.pizzashop.entities;

public class Order {
	private int cid;
	private int oid;
	private int mid;
	
	public Order() {
	}

	public Order(int cid, int oid, int mid) {
		super();
		this.cid = cid;
		this.oid = oid;
		this.mid = mid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
}
