package com.smartweb.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "center_tbl")
public class Center {
	@Id
	@SequenceGenerator(name = "catgory_sequence", sequenceName = "catgory_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catgory_sequence")
	private int id;
	private String name;
	private String logoAsLink;
	@Column(length = 50000000)
	private byte[] logoAsBytes;
	private String serverIP;
	private String serverPort;
	private String address;
	private String phone;
	
	public Center() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Center(int id, String name, String logoAsLink, byte[] logoAsBytes, String serverIP, String serverPort,
			String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.logoAsLink = logoAsLink;
		this.logoAsBytes = logoAsBytes;
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.address = address;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoAsLink() {
		return logoAsLink;
	}

	public void setLogoAsLink(String logoAsLink) {
		this.logoAsLink = logoAsLink;
	}

	public byte[] getLogoAsBytes() {
		return logoAsBytes;
	}

	public void setLogoAsBytes(byte[] logoAsBytes) {
		this.logoAsBytes = logoAsBytes;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}

