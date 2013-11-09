package com.processive.workshop.model.hb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="CAR")
public class Car {

	private int id;
	private int customerId;
	private String vendor;
	private String type;
	private String vendorCode;
	private String typeCode;
	private String licenseNumber;
	private String vin;
	private byte[] details;
	private Date buildDate;
	private Date tuevDate;
	private Date asuDate;
	
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
	
	@Column(name="VENDOR")
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="VENDOR_CODE")
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	@Column(name="TYPE_CODE")
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	@Column(name="LICENSE_NUMBER")
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	@Column(name="VIN")
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	@Column(name="DETAILS")
	public byte[] getDetails() {
		return details;
	}
	public void setDetails(byte[] details) {
		this.details = details;
	}
	
	@Column(name="BUILD_DATE")
        @Temporal(TemporalType.TIMESTAMP)
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	
	@Column(name="TUEV_DATE")
        @Temporal(TemporalType.TIMESTAMP)
	public Date getTuevDate() {
		return tuevDate;
	}
	public void setTuevDate(Date tuevDate) {
		this.tuevDate = tuevDate;
	}
	
	@Column(name="ASU_DATE")
        @Temporal(TemporalType.TIMESTAMP)
	public Date getAsuDate() {
		return asuDate;
	}
	public void setAsuDate(Date asuDate) {
		this.asuDate = asuDate;
	}
}
