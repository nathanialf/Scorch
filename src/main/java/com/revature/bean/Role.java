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
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class Role {

	@Id
	@Column(name="ROLE_ID")
	@SequenceGenerator(name="ROLEID_SEQ", sequenceName="ROLEID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLEID_SEQ")
	private int id;
	
	@Column(name="ROLE_NAME")
	private String name;

	public Role(){
		super();
	}
	
	public Role(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
