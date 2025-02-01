package com.graphql.performance.book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.graphql.performance.author.Author;

@DataJpaTest
class BookRepositoryTests
{
	private final BookRepository bookRepository;
	private final TestEntityManager entityManager;

	@Autowired
	public BookRepositoryTests(BookRepository bookRepository, TestEntityManager entityManager)
	{
		this.bookRepository = bookRepository;
		this.entityManager = entityManager;
	}

	@Test
	void shouldFindBooksByTitle()
	{
		// Setup test data
		Author author = new Author();
		author.setName("Test Author");
		entityManager.persist(author);

		Book book = new Book();
		book.setTitle("Spring Boot Testing");
		book.setAuthor(author);
		entityManager.persist(book);

		List<Book> found = bookRepository.findAllByTitleContainsIgnoreCase("Spring");
		assertThat(found).hasSize(1);
		assertThat(found.get(0).getTitle()).contains("Spring");
	}
}
