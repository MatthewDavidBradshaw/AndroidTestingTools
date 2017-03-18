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

package com.matthewtamlin.android_testing_tools.testing.espresso;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.matthewtamlin.android_testing_tools.library.espresso.EspressoHelper;
import com.matthewtamlin.android_testing_tools.testing.ThreeTextViewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.matthewtamlin.android_testing_tools.library.espresso.EspressoHelper.viewToViewInteraction;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Automated tests for the {@link EspressoHelper} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestEspressoHelper {
	/**
	 * Hosts three text views, each with unique text (available as static constants of the
	 * activity).
	 */
	@Rule
	public ActivityTestRule<ThreeTextViewActivity> rule = new ActivityTestRule<>(
			ThreeTextViewActivity.class);

	/**
	 * Test to ensure that the {@link EspressoHelper#viewToViewInteraction(View)} method functions
	 * correctly when only one view is being converted. The test will only pass if the correct
	 * ViewInteraction is returned, as determined by the text in the TextView it refers to.
	 */
	@Test
	public void testViewToViewInteraction_singleView() {
		final ThreeTextViewActivity activity = rule.getActivity();

		final ViewInteraction tv1ViewInteraction = viewToViewInteraction(activity.getTextView1());

		tv1ViewInteraction.check(hasText(ThreeTextViewActivity.TEXT_1));
	}

	/**
	 * Test to ensure that the {@link EspressoHelper#viewToViewInteraction(View, String)} method
	 * functions correctly when multiple view are being converted. The test will only pass if the
	 * correct ViewInteractions are returned, as determined by the text in the TextViews they refer
	 * to.
	 */
	@Test
	public void testViewToViewInteraction_multipleViews() {
		final ThreeTextViewActivity activity = rule.getActivity();

		final ViewInteraction tv1ViewInteraction = viewToViewInteraction(activity.getTextView1(),
				"1");
		final ViewInteraction tv2ViewInteraction = viewToViewInteraction(activity.getTextView2(),
				"2");
		final ViewInteraction tv3ViewInteraction = viewToViewInteraction(activity.getTextView3(),
				"3");

		tv1ViewInteraction.check(hasText(ThreeTextViewActivity.TEXT_1));
		tv2ViewInteraction.check(matches(withText(ThreeTextViewActivity.TEXT_2)));
		tv3ViewInteraction.check(matches(withText(ThreeTextViewActivity.TEXT_3)));
	}

	/**
	 * Creates a new ViewAssertion which checks that a TextView is displaying the specified text.
	 * The returned ViewAssertion will throw an AssertionError if the view it is applied to is null
	 * or is not a TextView. Furthermore, an AssertionError will be thrown if the TextView is not
	 * displaying the expected text.
	 *
	 * @param expectedText
	 * 		the text which is expected to be shown in the TextView this assertion is applied to, null
	 * 		allowed
	 * @return the ViewAssertion
	 */
	private ViewAssertion hasText(final CharSequence expectedText) {
		return new ViewAssertion() {
			@Override
			public void check(final View view, final NoMatchingViewException noViewFoundException) {
				if (view == null || !(view instanceof TextView)) {
					throw new AssertionError("view must be a non-null instance of TextView");
				} else {
					final TextView tv = (TextView) view;
					assertThat("text view has wrong text.", tv.getText(), is(expectedText));
				}
			}
		};
	}
}