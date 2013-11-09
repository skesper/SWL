package com.processive.workshop.model.hb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WORK_ITEM")
public class WorkItem {

	private int id;
	private int workOrderId;
	private byte[] description;
	private float price;
	private float vat;
	private float amount;
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="WORK_ORDER_ID")
	public int getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(int workOrderId) {
		this.workOrderId = workOrderId;
	}
	
	@Column(name="DESCRIPTION")
	public byte[] getDescription() {
		return description;
	}
	public void setDescription(byte[] description) {
		this.description = description;
	}
	
	@Column(name="PRICE")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Column(name="VAT")
	public float getVat() {
		return vat;
	}
	public void setVat(float vat) {
		this.vat = vat;
	}
	
	@Column(name="AMOUNT")
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
