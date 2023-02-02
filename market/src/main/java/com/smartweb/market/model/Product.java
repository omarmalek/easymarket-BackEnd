package com.smartweb.market.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product_tbl")
public class Product {
	@Id
	@SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
	private Long id;
	private int catgoryId;
	private String name;
	private double backagePrice;
	private double unitPrice;
	private double descountPrice;
	private String packType;//unitName
	private int numberOfPieces;
	private String img;
	private boolean avilable;
	private String weight;
	private String company;
	private String country;
	private String descreption;
	private int barcode;
	private String storeLocation;
	private int supplayerId;
	private Date dateOfProduct;
	private Date dateOfExpery;
	private int periodOfValidity;
	
	@ManyToMany(fetch = FetchType.EAGER,
				cascade = CascadeType.ALL)
	@JoinTable(name="prodct_images",
			   joinColumns = {
						@JoinColumn(name="product_id")},
			   inverseJoinColumns = {
						@JoinColumn(name="image_id")
	}	
			)
	private Set<ImageModel> productImages;
	
	public Product() {}
	
	public Product(int catgoryId, String name, double backagePrice, double unitPrice, double descountPrice,
			String packType, int numberOfPieces, String img, boolean avilable, String weight, String company,
			String country, String descreption, int barcode, String storeLocation, int supplayerId, Date dateOfProduct,
			Date dateOfExpery, int periodOfValidity) {
		super();
		this.catgoryId = catgoryId;
		this.name = name;
		this.backagePrice = backagePrice;
		this.unitPrice = unitPrice;
		this.descountPrice = descountPrice;
		this.packType = packType;
		this.numberOfPieces = numberOfPieces;
		this.img = img;
		this.avilable = avilable;
		this.weight = weight;
		this.company = company;
		this.country = country;
		this.descreption = descreption;
		this.barcode = barcode;
		this.storeLocation = storeLocation;
		this.supplayerId = supplayerId;
		this.dateOfProduct = dateOfProduct;
		this.dateOfExpery = dateOfExpery;
		this.periodOfValidity = periodOfValidity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCatgoryId() {
		return catgoryId;
	}
	public void setCatgoryId(int catgoryId) {
		this.catgoryId = catgoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBackagePrice() {
		return backagePrice;
	}
	public void setBackagePrice(double backagePrice) {
		this.backagePrice = backagePrice;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getDescountPrice() {
		return descountPrice;
	}
	public void setDescountPrice(double descountPrice) {
		this.descountPrice = descountPrice;
	}
	public String getPackType() {
		return packType;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}
	public int getNumberOfPieces() {
		return numberOfPieces;
	}
	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public boolean isAvilable() {
		return avilable;
	}
	public void setAvilable(boolean avilable) {
		this.avilable = avilable;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDescreption() {
		return descreption;
	}
	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public int getSupplayerId() {
		return supplayerId;
	}
	public void setSupplayerId(int supplayerId) {
		this.supplayerId = supplayerId;
	}
	public Date getDateOfProduct() {
		return dateOfProduct;
	}
	public void setDateOfProduct(Date dateOfProduct) {
		this.dateOfProduct = dateOfProduct;
	}
	public Date getDateOfExpery() {
		return dateOfExpery;
	}
	public void setDateOfExpery(Date dateOfExpery) {
		this.dateOfExpery = dateOfExpery;
	}
	public int getPeriodOfValidity() {
		return periodOfValidity;
	}
	public void setPeriodOfValidity(int periodOfValidity) {
		this.periodOfValidity = periodOfValidity;
	}

	public Set<ImageModel> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", catgoryId=" + catgoryId + ", name=" + name + ", backagePrice=" + backagePrice
				+ ", unitPrice=" + unitPrice + ", descountPrice=" + descountPrice + ", packType=" + packType
				+ ", numberOfPieces=" + numberOfPieces + ", img=" + img + ", avilable=" + avilable + ", weight="
				+ weight + ", company=" + company + ", country=" + country + ", descreption=" + descreption
				+ ", barcode=" + barcode + ", storeLocation=" + storeLocation + ", supplayerId=" + supplayerId
				+ ", dateOfProduct=" + dateOfProduct + ", dateOfExpery=" + dateOfExpery + ", periodOfValidity="
				+ periodOfValidity + ", productImages=" + productImages + "]";
	}
	

	
	




}
