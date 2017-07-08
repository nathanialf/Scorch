package com.revature.bean;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@SequenceGenerator(name="BATCH_SEQ", sequenceName="BATCH_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCH_SEQ")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User trainer;
	
	@Column(name="BATCH_NAME")
	private String name;
	
	@Column(name="BATCH_START")
	private Date startDate;
	
	@OneToMany(mappedBy="batch")
	private List<Week> weeks;

	public Batch() {
		super();
	}
	
	public Batch(User trainer, String name, Date startDate, List<Week> weeks) {
		super();
		this.trainer = trainer;
		this.name = name;
		this.startDate = startDate;
		this.weeks = weeks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
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

	@Override
	public String toString() {
		return "Batch [id=" + id + ", trainer=" + trainer + ", name=" + name + ", startDate=" + startDate + ", weeks="
				+ weeks + "]";
	}
	
}
