package com.smartweb.market.model;

import java.util.List;

import com.smartweb.market.entity.OrderRecordDAO;

public class UserOrderDTO {
	private long id;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private double cartTotal;
	private List<OrderRecordDAO> OrderCart;
	private String paymentType;//cash , paypal wallet, bank account  
	private String DelivaryServiceType;//urgent not urgent
	private long customerId;
	
	public UserOrderDTO() {
		
	}

	public UserOrderDTO(long id, String customerName, String customerPhone, String customerAddress, double cartTotal,
			List<OrderRecordDAO> orderCart, String paymentType, String delivaryServiceType, long customerId) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.cartTotal = cartTotal;
		OrderCart = orderCart;
		this.paymentType = paymentType;
		DelivaryServiceType = delivaryServiceType;
		this.customerId = customerId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public double getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}

	public List<OrderRecordDAO> getOrderCart() {
		return OrderCart;
	}

	public void setOrderCart(List<OrderRecordDAO> orderCart) {
		OrderCart = orderCart;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDelivaryServiceType() {
		return DelivaryServiceType;
	}

	public void setDelivaryServiceType(String delivaryServiceType) {
		DelivaryServiceType = delivaryServiceType;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	
	
}
