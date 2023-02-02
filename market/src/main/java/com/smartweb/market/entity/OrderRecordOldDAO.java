package com.smartweb.market.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "order_old_records")
public class OrderRecordOldDAO {
	@Id
	@SequenceGenerator(name = "order_old_record_sequence", sequenceName = "order_old_record_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_old_record_sequence")
	private long id;
	private long orderId;
	private long productId;
	private double productAmount;
	
	public OrderRecordOldDAO() {
		
	}

	public OrderRecordOldDAO(long id, long orderId, long productId, double productAmount) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productAmount = productAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}

	
	

}
