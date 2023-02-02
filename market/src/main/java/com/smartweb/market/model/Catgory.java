package com.smartweb.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "catgory_tbl", uniqueConstraints = {
		@UniqueConstraint(name = "catgory_columnname_unique", columnNames = "name") })
public class Catgory {

	@Id
	@SequenceGenerator(name = "catgory_sequence", sequenceName = "catgory_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catgory_sequence")
	private Long id;
	@Column(name = "name", nullable = false, length = 150, unique = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Catgory() {

	}

	public Catgory(String name) {
		
		this.name = name;
	}

	@Override
	public String toString() {
		return "Catgory [id=" + id + ", name=" + name + "]";
	}

}
