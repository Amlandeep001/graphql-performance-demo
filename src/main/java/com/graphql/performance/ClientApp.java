package com.graphql.performance;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;

import com.graphql.performance.book.Book;

import lombok.extern.log4j.Log4j2;

@Import(RestClientAutoConfiguration.class)
@Log4j2
public class ClientApp implements ApplicationRunner
{
	private final HttpSyncGraphQlClient client;

	public ClientApp(RestClient.Builder builder)
	{
		RestClient restClient = builder.baseUrl("http://localhost:8080/graphql").build();
		this.client = HttpSyncGraphQlClient.builder(restClient).build();
	}

	public static void main(String[] args)
	{
		new SpringApplicationBuilder(ClientApp.class).web(WebApplicationType.NONE).run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		log.info("Client App: Retrieving Book synchronously");
		var document = """
				query findBookById($id: Int!) {
				    book(id: $id) {
				        id
				        title
				        author {
				            id
				            name
				        }
				    }
				}
				""";
		var book = client.document(document)
				.variable("id", 1L)
				.retrieveSync("book")
				.toEntity(Book.class);
		log.info("Book: {}", book);

		// could use .retrieve() here for sync calls only catch is webflux needs to be on the classpath
	}
}
