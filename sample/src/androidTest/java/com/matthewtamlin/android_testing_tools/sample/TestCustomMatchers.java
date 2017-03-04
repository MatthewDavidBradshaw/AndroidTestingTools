package com.matthewtamlin.android_testing_tools.sample;

import android.support.test.runner.AndroidJUnit4;

import com.matthewtamlin.android_testing_tools.library.espresso.CustomMatchers;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.matthewtamlin.android_testing_tools.library.espresso.CustomMatchers.isExactType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit tests for the {@link CustomMatchers} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestCustomMatchers {
	/**
	 * A matcher which should match with Mammals, excluding subclasses.
	 */
	private Matcher mammalMatcher;

	/**
	 * Initialises testing resources.
	 */
	@Before
	public void setup() {
		mammalMatcher = isExactType(Mammal.class);
	}

	/**
	 * Test to verify that the matcher returned by {@link CustomMatchers#isExactType(Class)} does
	 * not match with instances of the target class superclass.
	 */
	@Test
	public void testIsExactType_superclassSupplied() {
		assertThat("Should not match Animal.", mammalMatcher.matches(new Animal()), is(false));
	}

	/**
	 * Test to verify that the matcher returned by {@link CustomMatchers#isExactType(Class)} matches
	 * with instances of the target class.
	 */
	@Test
	public void testIsExactType_exactClassSupplied() {
		assertThat("Should match Mammal.", mammalMatcher.matches(new Mammal()), is(true));
	}

	/**
	 * Test to verify that the matcher returned by {@link CustomMatchers#isExactType(Class)} does
	 * not match with instances of the target class subclass.
	 */
	@Test
	public void testIsExactType_subclassSupplied() {
		assertThat("Should not match Human.", mammalMatcher.matches(new Human()), is(false));
	}

	/**
	 * Test to verify that the matcher returned by {@link CustomMatchers#isExactType(Class)} does
	 * not match with null.
	 */
	@Test
	public void testIsExactType_nullSupplied() {
		final Matcher matcher = isExactType(Mammal.class);
		assertThat("Should not match null.", mammalMatcher.matches(null), is(false));
	}

	private static class Animal {}

	private static class Mammal extends Animal {}

	private static class Human extends Mammal {}
}