package com.processive.workshop.model.hb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="WORK_ORDER")
public class WorkOrder {

	private int id;
	private int customerId;
	private int carId;
	private byte[] description;
	private Date orderDate;
	private Date dueDate;
	private String offerPrice;
	private String kmState;
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="CUSTOMER_ID")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@Column(name="CAR_ID")
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	@Column(name="DESCRIPTION")
	public byte[] getDescription() {
		return description;
	}
	public void setDescription(byte[] description) {
		this.description = description;
	}
	
	@Column(name="ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name="DUE_DATE")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name="OFFER_PRICE")
	public String getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}
	
	
	@Column(name="KM_STATE", length=50)
	public String getKmState() {
		return kmState;
	}
	public void setKmState(String kmState) {
		this.kmState = kmState;
	}
}
