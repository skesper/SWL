package com.processive.workshop.model.hb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BILL")
public class Bill {

	private int id;
	private int workorderId;
	private Date billDate;
	private int billStatus;
	private Date dueDate;
	private Date payDate;
	private int number;
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="WORK_ORDER_ID")
	public int getWorkorderId() {
		return workorderId;
	}
	public void setWorkorderId(int workorderId) {
		this.workorderId = workorderId;
	}

	@Column(name="BILL_DATE")
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	@Column(name="BILL_STATUS")
	public int getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	@Column(name="DUE_DATE")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name="PAY_DATE")
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Column(name="NUMBER")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
