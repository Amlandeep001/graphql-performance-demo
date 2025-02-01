package com.graphql.performance.review;

public record ReviewFilter(
		Integer rating,
		Boolean verified,
		String reviewerName)
{
}
