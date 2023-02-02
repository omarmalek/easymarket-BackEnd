package com.smartweb.market.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "orders_tbl")
public class OrderDAO {
	@Id
	@SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
	private long id;
	private long customerId;
	private int orderSetterId; 
	private int deliveryManId;
	private double delivaryCharge;
	
	private double cartTotal;
	@CreationTimestamp
	private LocalDateTime date;
	private String paymentType;//cash , paypal wallet, bank account  
	private String delivaryServiceType;//urgent not urgent
	private boolean isPacked ;
	private boolean isSentDelivery;
	private boolean isDelivered ;
	private boolean isPaid;
	private boolean isCancelled;
	private boolean isRejected;
	private String customerEvaluation;
	private String controlNotes;
	
	public OrderDAO() {
		
	}

	public OrderDAO(long id, long customerId, int orderSetterId, int deliveryManId, double delivaryCharge,
			double cartTotal, LocalDateTime date, String paymentType, String delivaryServiceType, boolean isPacked,
			boolean isSentDelivery, boolean isDelivered, boolean isPaid, boolean isCancelled, boolean isRejected,
			String customerEvaluation, String controlNotes) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.orderSetterId = orderSetterId;
		this.deliveryManId = deliveryManId;
		this.delivaryCharge = delivaryCharge;
		this.cartTotal = cartTotal;
		this.date = date;
		this.paymentType = paymentType;
		this.delivaryServiceType = delivaryServiceType;
		this.isPacked = isPacked;
		this.isSentDelivery = isSentDelivery;
		this.isDelivered = isDelivered;
		this.isPaid = isPaid;
		this.isCancelled = isCancelled;
		this.isRejected = isRejected;
		this.customerEvaluation = customerEvaluation;
		this.controlNotes = controlNotes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
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
		return delivaryServiceType;
	}

	public void setDelivaryServiceType(String delivaryServiceType) {
		this.delivaryServiceType = delivaryServiceType;
	}

	public boolean isPacked() {
		return isPacked;
	}

	public void setPacked(boolean isPacked) {
		this.isPacked = isPacked;
	}

	public boolean isSentDelivery() {
		return isSentDelivery;
	}

	public void setSentDelivery(boolean isSentDelivery) {
		this.isSentDelivery = isSentDelivery;
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

	@Override
	public String toString() {
		return "DatabaseOrder [id=" + id + ", customerId=" + customerId + ", orderSetterId=" + orderSetterId
				+ ", deliveryManId=" + deliveryManId + ", delivaryCharge=" + delivaryCharge + ", cartTotal=" + cartTotal
				+ ", date=" + date + ", paymentType=" + paymentType + ", delivaryServiceType=" + delivaryServiceType
				+ ", isPacked=" + isPacked + ", isSentDelivery=" + isSentDelivery + ", isDelivered=" + isDelivered
				+ ", isPaid=" + isPaid + ", isCancelled=" + isCancelled + ", isRejected=" + isRejected
				+ ", customerEvaluation=" + customerEvaluation + ", controlNotes=" + controlNotes + "]";
	}
	
	
}
