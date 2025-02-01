package com.graphql.performance.author;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
class AuthorControllerTests
{
	private final GraphQlTester graphQlTester;

	@Autowired
	public AuthorControllerTests(GraphQlTester graphQlTester)
	{
		this.graphQlTester = graphQlTester;
	}

	@Test
	void shouldBatchLoadBooksForAuthors()
	{
		String document = """
				    query {
				        authors {
				            id
				            name
				            books {
				                id
				                title
				            }
				        }
				    }
				""";

		graphQlTester.document(document)
				.execute()
				.path("authors")
				.entityList(Author.class)
				.satisfies(authors ->
				{
					assertThat(authors).isNotEmpty();
					assertThat(authors).allMatch(author -> !author.getBooks().isEmpty());
				});
	}
}
