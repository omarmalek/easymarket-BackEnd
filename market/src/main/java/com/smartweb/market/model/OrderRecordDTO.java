package com.smartweb.market.model;

public class OrderRecordDTO {
	private long id;
	private long orderId;
	private long productId;
	private String productName;
	private double unitPrice;
	private double productAmount;
	private String packType;//unitName
	
	public OrderRecordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderRecordDTO(long id, long orderId, long productId, String productName, double unitPrice,
			double productAmount, String packType) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.productAmount = productAmount;
		this.packType = packType;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	@Override
	public String toString() {
		return "OrderRecordDTO [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", productName="
				+ productName + ", unitPrice=" + unitPrice + ", productAmount=" + productAmount + ", packType="
				+ packType + "]";
	}

	
	
	
}
