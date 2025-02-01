package com.graphql.performance.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long>
{
	List<Author> findAllByNameContainsIgnoreCase(String name);

	@Query("SELECT a FROM Author a LEFT JOIN FETCH a.books")
	List<Author> findAllWithBooks();
}
