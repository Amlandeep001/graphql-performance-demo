package com.graphql.performance.book;

import com.graphql.performance.author.Author;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String title;

	@ManyToOne(fetch = FetchType.LAZY)
	Author author;
}
