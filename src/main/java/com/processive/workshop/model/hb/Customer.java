package com.processive.workshop.model.hb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	private int id=-1;
	private int billAddressId;
	private int gender;
	private String firstName;
	private String lastName;
	private int title;
	private String company;
	private String phone;
	private String mobilePhone;
	private String fax;
	private String email;
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="BILL_ADDRESS_ID")
	public int getBillAddressId() {
		return billAddressId;
	}
	public void setBillAddressId(int billAddressId) {
		this.billAddressId = billAddressId;
	}
	
	@Column(name="GENDER")
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	@Column(name="FIRSTNAME", length=50)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LASTNAME", length=50)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="TITLE")
	public int getTitle() {
		return title;
	}
	public void setTitle(int title) {
		this.title = title;
	}
	
	@Column(name="COMPANY", length=50)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Column(name="PHONE", length=50)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="MOBILE_PHONE", length=50)
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Column(name="FAX", length=50)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name="EMAIL", length=100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
