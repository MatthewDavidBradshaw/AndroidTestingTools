package com.matthewtamlin.android_testing_tools.library.espresso;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CustomMatchers {
	/**
	 * Creates a new matcher that matches objects which are an instance of the provided class.
	 * Subclasses are explicitly excluded.
	 *
	 * @param type
	 * 		the type to match with
	 * @param <T>
	 * 		the type of objects which this matcher can be applied to
	 * @return the matcher
	 */
	public static <T> Matcher<T> isExactType(final Class type) {
		return new TypeSafeMatcher<T>() {
			@Override
			protected boolean matchesSafely(final T item) {
				if (item == null) {
					return false;
				}

				return item.getClass() == type;
			}

			@Override
			public void describeTo(final Description description) {
				description.appendText("is exactly type " + type.getName());
			}
		};
	}
}