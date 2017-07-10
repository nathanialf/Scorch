package com.revature.bean;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "BATCH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class Batch {
	
	@Id
	@Column(name="BATCH_ID")
	@SequenceGenerator(name="BATCHID_SEQ", sequenceName="BATCHID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCHID_SEQ")
	private int id;
	
	@Column(name="BATCH_NAME")
	private String name;
	
	@Column(name="BATCH_START")
	private Date startDate;

	@OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="WEEK_ID")
	private List<Week> weeks;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="BATCH_ASSOC", joinColumns = @JoinColumn(name="BATCH_ID"), inverseJoinColumns = @JoinColumn(name="ASSOCIATE_ID"))
	private Set<User> associates = new HashSet<>();
	
	public Batch() {
		super();
	}
	
	public Batch(String name, Date startDate) {
		super();
		this.name = name;
		this.startDate = startDate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}

	public Set<User> getAssociates() {
		return associates;
	}

	public void setAssociates(Set<User> associates) {
		this.associates = associates;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", name=" + name + ", startDate=" + startDate + ", weeks=" + weeks + "]";
	}
}
