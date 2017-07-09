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
@Table(name="TOPICRATING")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class TopicRating {
	
	@Id
	@Column(name="RATING_ID")
	@SequenceGenerator(name="RATINGID_SEQ", sequenceName="RATINGID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RATINGID_SEQ")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="TOPIC_ID")
	private Topic topic;
	
	@Column(name="RATING_RATING")
	private int rating;
	
	public TopicRating(){
		super();
	}

	public TopicRating(int id, User user, Topic topic, int rating) {
		super();
		this.id = id;
		this.user = user;
		this.topic = topic;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "TopicRating [id=" + id + ", user=" + user + ", topic=" + topic + ", rating=" + rating + "]";
	}
	
}
