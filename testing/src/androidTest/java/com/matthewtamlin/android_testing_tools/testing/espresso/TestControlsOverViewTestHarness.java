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

package com.matthewtamlin.android_testing_tools.testing.espresso;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.LinearLayout;

import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsOverViewTestHarness;
import com.matthewtamlin.android_testing_tools.testing.StubControlsOverViewTestHarness;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.GONE;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsOverView_innerControls;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsOverView_outerControls;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsOverView_root;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsOverView_testViewContainer;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Automated tests for the {@link ControlsOverViewTestHarness} class.
 */
public class TestControlsOverViewTestHarness {
	@Rule
	public ActivityTestRule<StubControlsOverViewTestHarness> rule =
			new ActivityTestRule<>(StubControlsOverViewTestHarness.class);

	private StubControlsOverViewTestHarness activity;

	@Before
	public void setup() {
		activity = rule.getActivity();
	}

	@Test
	public void testInitialState() {
		final LinearLayout controls = activity.getInnerControlsContainer();

		// Check all expected controls are present
		assertThat("Incorrect number of controls.", controls.getChildCount(), is(3));
		assertThat(controls.getChildAt(0), is(activity.controlView1));
		assertThat(controls.getChildAt(1), is(activity.controlView2));
		assertThat(controls.getChildAt(2), is(activity.controlView3));

		// Check all controls are visible
		onView(withId(controlsOverView_outerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));
		onView(withId(controlsOverView_innerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));

		// Check returned controls collection
		final List<View> expectedControls = new ArrayList<>();
		expectedControls.add(activity.controlView1);
		expectedControls.add(activity.controlView2);
		expectedControls.add(activity.controlView3);
		assertThat(activity.getControls(), is(expectedControls));

		// Check test view is displayed correctly
		assertThat(activity.getTestViewContainer().getChildAt(0), is(activity.testView));
	}

	@Test
	public void testGetRootView() {
		final View expectedView = activity.findViewById(controlsOverView_root);
		assertThat(activity.getRootView(), is(expectedView));
	}

	@Test
	public void testGetInnerControlsContainer() {
		final View expectedView = activity.findViewById(controlsOverView_innerControls);
		assertThat(activity.getInnerControlsContainer(), is(expectedView));
	}

	@Test
	public void testGetOuterControlsContainer() {
		final View expectedView = activity.findViewById(controlsOverView_outerControls);
		assertThat(activity.getOuterControlsContainer(), is(expectedView));
	}

	@Test
	public void testGetTestViewContainer() {
		final View expectedView = activity.findViewById(controlsOverView_testViewContainer);
		assertThat(activity.getTestViewContainer(), is(expectedView));
	}

	@Test
	public void testEnableAndDisableControls() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activity.enableControls(false);
			}
		});

		onView(withId(controlsOverView_outerControls))
				.check(matches(withEffectiveVisibility(GONE)));
		onView(withId(controlsOverView_innerControls))
				.check(matches(withEffectiveVisibility(GONE)));

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activity.enableControls(true);
			}
		});

		onView(withId(controlsOverView_outerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));
		onView(withId(controlsOverView_innerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activity.enableControls(false);
			}
		});

		onView(withId(controlsOverView_outerControls))
				.check(matches(withEffectiveVisibility(GONE)));
		onView(withId(controlsOverView_innerControls))
				.check(matches(withEffectiveVisibility(GONE)));

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activity.enableControls(true);
			}
		});

		onView(withId(controlsOverView_outerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));
		onView(withId(controlsOverView_innerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));
	}
}
