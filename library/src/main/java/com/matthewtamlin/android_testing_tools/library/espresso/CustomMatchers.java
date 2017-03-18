/*
 * Copyright 2017 Matthew Tamlin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matthewtamlin.android_testing_tools.library.espresso;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Custom Hamcrest matchers.
 */
public class CustomMatchers {
	/**
	 * Creates a new matcher that matches with objects of the supplied type, excluding subclasses.
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