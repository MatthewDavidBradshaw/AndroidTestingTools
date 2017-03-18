package com.matthewtamlin.android_testing_tools.testing.espresso;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.LinearLayout;

import com.matthewtamlin.android_testing_tools.testing.StubControlsAboveViewTestHarness;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_innerControls;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_outerControls;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_root;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_testViewContainer;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestControlsAboveViewTestHarness {
	@Rule
	public ActivityTestRule<StubControlsAboveViewTestHarness> rule =
			new ActivityTestRule<>(StubControlsAboveViewTestHarness.class);

	private StubControlsAboveViewTestHarness activity;

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
		onView(withId(controlsAboveView_outerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));
		onView(withId(controlsAboveView_innerControls))
				.check(matches(withEffectiveVisibility(VISIBLE)));
	}

	@Test
	public void testGetRootView() {
		final View expectedView = activity.findViewById(controlsAboveView_root);
		assertThat(activity.getRootView(), is(expectedView));
	}

	@Test
	public void testGetInnerControlsContainer() {
		final View expectedView = activity.findViewById(controlsAboveView_innerControls);
		assertThat(activity.getRootView(), is(expectedView));
	}

	@Test
	public void testGetOuterControlsContainer() {
		final View expectedView = activity.findViewById(controlsAboveView_outerControls);
		assertThat(activity.getRootView(), is(expectedView));
	}

	@Test
	public void testGetTestViewContainer() {
		final View expectedView = activity.findViewById(controlsAboveView_testViewContainer);
		assertThat(activity.getRootView(), is(expectedView));
	}
}