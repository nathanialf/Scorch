package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="REVIEWTYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class ReviewType {

	@Id
	@Column(name="REVIEWTYPE_ID")
	@SequenceGenerator(name="REVIEWTYPEID_SEQ", sequenceName="REVIEWTYPEID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEWTYPEID_SEQ")
	private int id;
	
	@Column(name="REVIEWTYPE_TYPE")
	private String type;

	public ReviewType(String type) {
		super();
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReviewType [id=" + id + ", type=" + type + "]";
	}
}
