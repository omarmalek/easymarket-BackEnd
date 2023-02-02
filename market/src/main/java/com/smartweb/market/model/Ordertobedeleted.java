package com.smartweb.market.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "order_tbl")
public class Ordertobedeleted {
	@Id
	@SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
	private long id;
	private int customerId;
	private int orderSetterId; 
	private int deliveryManId;
	private double delivaryCharge;
	//private List<OrderRecord> OrderCart;
	private double cartTotal;
	@CreationTimestamp
	private LocalDateTime date;
	private String paymentType;//cash , paypal wallet, bank account  
	private String DelivaryServiceType;//urgent not urgent
	private boolean isPacked ;
	private boolean isDelivered ;
	private boolean isPaid;
	private boolean isCancelled;
	private boolean isRejected;
	private String customerEvaluation;
	private String controlNotes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getOrderSetterId() {
		return orderSetterId;
	}
	public void setOrderSetterId(int orderSetterId) {
		this.orderSetterId = orderSetterId;
	}
	public int getDeliveryManId() {
		return deliveryManId;
	}
	public void setDeliveryManId(int deliveryManId) {
		this.deliveryManId = deliveryManId;
	}
	public double getDelivaryCharge() {
		return delivaryCharge;
	}
	public void setDelivaryCharge(double delivaryCharge) {
		this.delivaryCharge = delivaryCharge;
	}
	public double getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
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
	public boolean isPacked() {
		return isPacked;
	}
	public void setPacked(boolean isPacked) {
		this.isPacked = isPacked;
	}
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public boolean isCancelled() {
		return isCancelled;
	}
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public boolean isRejected() {
		return isRejected;
	}
	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}
	public String getCustomerEvaluation() {
		return customerEvaluation;
	}
	public void setCustomerEvaluation(String customerEvaluation) {
		this.customerEvaluation = customerEvaluation;
	}
	public String getControlNotes() {
		return controlNotes;
	}
	public void setControlNotes(String controlNotes) {
		this.controlNotes = controlNotes;
	}
	
	public Ordertobedeleted() {
		
	}
	public Ordertobedeleted(long id, int customerId, int orderSetterId, int deliveryManId, double delivaryCharge, double cartTotal,
			LocalDateTime date, String paymentType, String delivaryServiceType, boolean isPacked, boolean isDelivered,
			boolean isPaid, boolean isCancelled, boolean isRejected, String customerEvaluation, String controlNotes) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.orderSetterId = orderSetterId;
		this.deliveryManId = deliveryManId;
		this.delivaryCharge = delivaryCharge;
		this.cartTotal = cartTotal;
		this.date = date;
		this.paymentType = paymentType;
		DelivaryServiceType = delivaryServiceType;
		this.isPacked = isPacked;
		this.isDelivered = isDelivered;
		this.isPaid = isPaid;
		this.isCancelled = isCancelled;
		this.isRejected = isRejected;
		this.customerEvaluation = customerEvaluation;
		this.controlNotes = controlNotes;
	}
	
}
