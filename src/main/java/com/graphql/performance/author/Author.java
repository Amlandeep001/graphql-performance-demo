package com.graphql.performance.author;

import java.util.List;

import com.graphql.performance.book.Book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String name;

	@OneToMany(mappedBy = "author")
	List<Book> books;
}
