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
@Table(name="REVIEW")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="tapCache")
public class Review {

	@Id
	@Column(name="REVIEW_ID")
	@SequenceGenerator(name="REVIEWID_SEQ", sequenceName="REVIEWID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEWID_SEQ")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="WEEK_ID")
	private Week week;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="REVIEWTYPE_ID")
	private ReviewType type;
	
	@Column(name="REVIEW_REVIEW")
	private String review;
	
	@Column(name="REVIEW_RATING")
	private int rating;
	
	public Review(){
		super();
	}

	public Review(User user, Week week, ReviewType type, String review, int rating) {
		super();
		this.user = user;
		this.week = week;
		this.type = type;
		this.review = review;
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

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public ReviewType getType() {
		return type;
	}

	public void setType(ReviewType type) {
		this.type = type;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", user=" + user + ", type=" + type + ", review=" + review + ", rating=" + rating
				+ "]";
	}
	
	
}
