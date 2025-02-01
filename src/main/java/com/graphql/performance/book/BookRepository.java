package com.graphql.performance.book;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>
{

	List<Book> findAllByTitleContainsIgnoreCase(String title);

	Arrays findAllByAuthorIdIn(List<Long> authorIds);

	List<Book> findByAuthorIdIn(List<Long> authorIds);
}
