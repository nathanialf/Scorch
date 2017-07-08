package com.revature.bean;

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
@Table(name="WEEK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class Week {
	
	@Id
	@Column(name="WEEK_ID")
	@SequenceGenerator(name="WEEKID_SEQ", sequenceName="WEEKID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEEKID_SEQ")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="BATCH_ID")
	private Batch batch;
	
	@Column(name="WEEK_NUM")
	private int num;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="TOPIC_ID")
	private List<Topic> topics;
	
	public Week(){
		super();
	}

	public Week(Batch batch, int num, List<Topic> topics) {
		super();
		this.batch = batch;
		this.num = num;
		this.topics = topics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Week [id=" + id + ", num=" + num + ", topics=" + topics + "]";
	}
}
