package com.graphql.performance.author;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.graphql.performance.book.Book;
import com.graphql.performance.book.BookRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class AuthorController
{
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository)
	{
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@QueryMapping
	public List<Author> authors()
	{
		// return authorRepository.findAllWithBooks();
		return authorRepository.findAll();
	}

	@SchemaMapping
	public List<Book> booksWithDelay(Author author) throws InterruptedException
	{
		// this could be a call to some microservice would retrieve books by authorId
		log.info("Retrieving books for author {}", author.getName());
		Thread.sleep((1000));
		return new ArrayList<>();
	}

	@BatchMapping
	public List<List<Book>> books(List<Author> authors)
	{
		log.info("Batch loading books for {} authors", authors.size());

		// Get all author IDs
		List<Long> authorIds = authors.stream()
				.map(Author::getId)
				.toList();

		// Make a single query to get all books for all authors
		List<Book> allBooks = bookRepository.findByAuthorIdIn(authorIds);

		// Group books by author ID
		Map<Long, List<Book>> booksByAuthorId = allBooks.stream()
				.collect(Collectors.groupingBy(book -> book.getAuthor().getId()));

		// Map back to original author order
		return authors.stream()
				.map(author -> booksByAuthorId.getOrDefault(author.getId(), Collections.emptyList()))
				.toList();
	}

}
