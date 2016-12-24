package com.matthewtamlin.android_testing_tools.sample;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.matthewtamlin.android_testing_tools.library.EspressoHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the DemoCustomView using the DemoTestHarness.
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTestDemoCustomView {
	/**
	 * Hosts the view being tested.
	 */
	@Rule
	private ActivityTestRule<DemoTestHarness> rule = new ActivityTestRule<>(DemoTestHarness.class);

	/**
	 * The view being tested, as a direct View reference.
	 */
	private DemoCustomView testView;

	/**
	 * The view being tested, as an espresso ViewInteraction.
	 */
	private ViewInteraction testViewEspresso;

	/**
	 * Gets the references to the test view.
	 */
	@Before
	public void setUp() {
		testView = rule.getActivity().getTestView();
		testViewEspresso = EspressoHelper.viewToViewInteraction(testView);
	}

	@Test
	public void test_foo() {
		// Tests go here
	}
}