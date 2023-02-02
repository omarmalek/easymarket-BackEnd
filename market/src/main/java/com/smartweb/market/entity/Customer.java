package com.smartweb.market.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer_tbl")
public class Customer {
	@Id
	@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
	private long id;
	private String name;
	private String phoneNumber;
	private String whatsappAcount;
	private String address;
	private String city;
	
	public Customer() {
		
	}
	public Customer(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Customer(long id, String name, String phoneNumber, String whatsappAcount, String address, String city) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.whatsappAcount = whatsappAcount;
		this.address = address;
		this.city = city;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getWhatsappAcount() {
		return whatsappAcount;
	}
	public void setWhatsappAcount(String whatsappAcount) {
		this.whatsappAcount = whatsappAcount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	

}
