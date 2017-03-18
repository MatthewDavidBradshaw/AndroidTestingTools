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

package com.matthewtamlin.android_testing_tools.sample;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.matthewtamlin.android_testing_tools.library.espresso.EspressoHelper;
import com.matthewtamlin.android_testing_tools.library.espresso.TypeSafeViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoTestDemoCustomView {
	@Rule
	private ActivityTestRule<DemoTestHarness> rule = new ActivityTestRule<>(DemoTestHarness.class);

	private DemoCustomView testView;

	private ViewInteraction testViewEspresso;

	@Before
	public void setUp() {
		testView = rule.getActivity().getTestView();
		testViewEspresso = EspressoHelper.viewToViewInteraction(testView);

		/*
		 * NOTE: The above method only works when there is one view being converted. If that
		 * isn't the case, an alternative method needs to be used as follows:
		 *
		 * 		testViewEspresso1 = EspressoHelper.viewToViewInteraction(testView1, "1");
		 * 		testViewEspresso2 = EspressoHelper.viewToViewInteraction(testView2, "foo");
		 * 		testViewEspresso3 = EspressoHelper.viewToViewInteraction(testView3, "some other " +
		 * 			"unique ID");
		 */
	}

	@Test
	public void test_foo() {
		// Test goes here
	}

	private static TypeSafeViewAction<DemoCustomView> doSomething() {
		return new TypeSafeViewAction<DemoCustomView>() {
			@Override
			public void typeSafePerform(final UiController uiController,
					final DemoCustomView view) {
				view.doSomething();
			}

			@Override
			public String getDescription() {
				return "do something";
			}
		};
	}
}