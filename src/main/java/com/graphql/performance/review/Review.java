package com.graphql.performance.review;

import java.time.LocalDateTime;

import com.graphql.performance.book.Book;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.PackagePrivate;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Review
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Integer rating; // 1-5 stars
	String comment;
	LocalDateTime createdAt;
	String reviewerName;
	Boolean verified; // verified purchase

	@ManyToOne(fetch = FetchType.LAZY)
	Book book;

	@PrePersist
	@PackagePrivate
	void onCreate()
	{
		createdAt = LocalDateTime.now();
	}

	// Constructors
	/*public Review() {
	
	}*/

	// Getters and Setters
	/*public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public Integer getRating() {
	    return rating;
	}
	
	public void setRating(Integer rating) {
	    this.rating = rating;
	}
	
	public String getComment() {
	    return comment;
	}
	
	public void setComment(String comment) {
	    this.comment = comment;
	}
	
	public LocalDateTime getCreatedAt() {
	    return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
	    this.createdAt = createdAt;
	}
	
	public String getReviewerName() {
	    return reviewerName;
	}
	
	public void setReviewerName(String reviewerName) {
	    this.reviewerName = reviewerName;
	}
	
	public Boolean getVerified() {
	    return verified;
	}
	
	public void setVerified(Boolean verified) {
	    this.verified = verified;
	}
	
	public Book getBook() {
	    return book;
	}
	
	public void setBook(Book book) {
	    this.book = book;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || getClass() != o.getClass()) return false;
	    Review review = (Review) o;
	    return Objects.equals(id, review.id) && Objects.equals(rating, review.rating) && Objects.equals(comment, review.comment) && Objects.equals(createdAt, review.createdAt) && Objects.equals(reviewerName, review.reviewerName) && Objects.equals(verified, review.verified) && Objects.equals(book, review.book);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(id, rating, comment, createdAt, reviewerName, verified, book);
	}
	
	@Override
	public String toString() {
	    return "Review{" +
	            "id=" + id +
	            ", rating=" + rating +
	            ", comment='" + comment + '\'' +
	            ", createdAt=" + createdAt +
	            ", reviewerName='" + reviewerName + '\'' +
	            ", verified=" + verified +
	            ", book=" + book +
	            '}';
	}*/
}
