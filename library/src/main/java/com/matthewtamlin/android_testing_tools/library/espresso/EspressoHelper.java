/*
 * Copyright 2016 Matthew Tamlin
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


import android.support.test.espresso.ViewInteraction;
import android.view.View;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static com.matthewtamlin.android_testing_tools.library.R.id.espresso_util_conversion_tag;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * Helper class for converting View objects to ViewInteraction objects.
 */
public class EspressoHelper {
	/**
	 * Creates a ViewInteractor for the supplied view. This method can only be used on a single view
	 * in the view hierarchy. To get ViewInteractions for multiple views in the same hierarchy, use
	 * {@link #viewToViewInteraction(View, String)} instead.
	 *
	 * @param view
	 * 		the view to get a ViewInteractor for, not null
	 * @return a ViewInteractor representing the supplied view, not null
	 * @throws IllegalArgumentException
	 * 		if {@code view} is null
	 */
	public static synchronized ViewInteraction viewToViewInteraction(final View view) {
		checkNotNull(view, "view cannot be null");

		// Set the tag to uniquely identify the view
		view.setTag(espresso_util_conversion_tag, "test");

		// Find the view using the tag
		return onView(withTagKey(espresso_util_conversion_tag));
	}

	/**
	 * Creates a ViewInteractor for the supplied view. This method can be used on multiple views
	 * in the view hierarchy, so long as the {@code uniqueTag} argument is unique to each call.
	 *
	 * @param view
	 * 		the view to get a ViewInteractor for, not null
	 * @param uniqueTag
	 * 		a value which is unique in the current view hierarchy
	 * @return a ViewInteractor representing the supplied view, not null
	 * @throws IllegalArgumentException
	 * 		if {@code view} is null
	 */
	public static synchronized ViewInteraction viewToViewInteraction(final View view,
			final String uniqueTag) {
		checkNotNull(view, "view cannot be null");

		// Set the tag to uniquely identify the view
		view.setTag(espresso_util_conversion_tag, uniqueTag);

		// Find the view using the tag
		return onView(withTagKey(espresso_util_conversion_tag, is((Object) uniqueTag)));
	}
}