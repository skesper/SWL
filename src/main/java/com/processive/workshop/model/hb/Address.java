package com.processive.workshop.model.hb;

import javax.persistence.*;

@Entity
@Table(name="ADDRESS")
public class Address {

	private int id;
	private int customerId;
	private int addressType;
	private String street1;
	private String street2;
	private String houseNumber;
	private int zipCode;
	private String city;
	private String country;
	
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
	
	@Column(name="ADDRESS_TYPE")
	public int getAddressType() {
		return addressType;
	}
	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}
	
	@Column(name="STREET1", length=50)
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	@Column(name="STREET2", length=50)
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	@Column(name="HOUSENUMBER", length=20)
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	@Column(name="ZIPCODE")
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name="CITY", length=50)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
