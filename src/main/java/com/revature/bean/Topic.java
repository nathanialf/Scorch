package com.revature.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="TOPIC")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class Topic {

	@Id
	@Column(name="TOPIC_ID")
	@SequenceGenerator(name="TOPICID_SEQ", sequenceName="TOPICID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TOPICID_SEQ")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="WEEK_ID")
	private Week week;
	
	@Column(name="TOPIC_TOPIC")
	private String topic;
	
	public Topic(){
		super();
	}

	public Topic(Week week, String topic) {
		super();
		this.week = week;
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", week=" + week + ", topic=" + topic + "]";
	}
	
	
}
