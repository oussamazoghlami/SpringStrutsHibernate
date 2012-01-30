package com.tunisiana.tutorials.util;

import static com.google.common.base.Preconditions.checkArgument;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

/**
 * Simple Class allowing to test some Guava Methods
 * 
 * @author Oussama ZOGHLAMI
 * 
 */
public class TestGuava {
	
	@Test(expected = IllegalArgumentException.class)
	public void testGuavaPreconditions() {
		String name = null;
		checkArgument(name != null, "The argument %s could not be null !", name);
	}

	@Test
	public void testGuavaOptional() {
		Integer i = 5;
		Optional<Integer> optional = Optional.fromNullable(i);
		assertTrue(optional.isPresent());
		assertEquals(i, optional.get());

		Integer j = null;
		Optional<Integer> optional2 = Optional.fromNullable(j);
		assertFalse(optional2.isPresent());
	}

	@Test
	public void testGuavaOrdering() {
		// Define the Guava Comparator
		Ordering<String> byLengthComparator = new Ordering<String>() {
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}

		};

		// Prepare a not sorted list
		List<String> names = Lists.newArrayList("Hahi", "Didier", "Bedis", "B", "Oussama", "C");

		// Sort the list
		Collections.sort(names, byLengthComparator.reverse().compound(Ordering.natural().reverse()));
		assertEquals("[Oussama, Didier, Bedis, Hahi, C, B]", names.toString());
	}

	@Test
	public void testGuavaLoadingCache() throws ExecutionException {
		// Define the cache loader
		CacheLoader<String, String> greetingCacheLoader = new CacheLoader<String, String>() {

			@Override
			public String load(String key) throws Exception {
				return "Hello " + key;
			}

		};

		// Define the loading cache
		LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000)
				.expireAfterWrite(10, TimeUnit.MINUTES).build(greetingCacheLoader);

		assertEquals("Hello test", cache.get("test"));
		assertEquals("Hello hahi", cache.get("hahi"));
		cache.get("hahi");
		cache.get("test");
		cache.get("hahi");
		assertEquals(2, cache.stats().loadCount());
		assertEquals(5, cache.stats().requestCount());
	}

	@Test
	public void testFunctionalFunction() {
		Function<String, Integer> lengthMapper = new Function<String, Integer>() {

			@Override
			public Integer apply(String input) {
				return input == null ? 0 : input.length();
			}
		};
		
		assertEquals(0, (int) lengthMapper.apply(null));
		assertEquals(0, (int) lengthMapper.apply(""));
		assertEquals(4, (int) lengthMapper.apply("hahi"));
	}
	
	@Test
	public void testGuavaStringsUtil() {
		// Test String Joiner
		Joiner joiner = Joiner.on("; ").skipNulls();
		String result = joiner.join("Hello","Mr","Ghost",null,"Man");
		assertEquals("Hello; Mr; Ghost; Man", result);
		
		// Test Splitter Method
		Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
		assertEquals("[foo, bar, qux]", Iterables.toString(splitter.split("foo,bar,,   qux")));
		
		// CharMatcher
		assertEquals("22275037", CharMatcher.DIGIT.retainFrom("Tel Number: 22275037"));
		assertTrue(CharMatcher.JAVA_UPPER_CASE.matchesAllOf("AAABBBBCC"));
	}

}
