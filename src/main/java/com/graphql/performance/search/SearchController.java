package com.graphql.performance.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.graphql.performance.author.AuthorRepository;
import com.graphql.performance.book.BookRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class SearchController
{
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public SearchController(BookRepository bookRepository, AuthorRepository authorRepository)
	{
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@QueryMapping
	List<Object> search(@Argument String text)
	{
		log.debug("Searching for '" + text + "'");
		List<Object> results = new ArrayList<>();
		results.addAll(authorRepository.findAllByNameContainsIgnoreCase(text));
		results.addAll(bookRepository.findAllByTitleContainsIgnoreCase(text));
		return results;
	}
}
