package com.matthewtamlin.android_testing_tools.testing.espresso;

import android.support.test.rule.ActivityTestRule;

import com.matthewtamlin.android_testing_tools.testing.StubControlsAboveViewTestHarness;

import org.junit.Before;
import org.junit.Rule;

public class TestControlsAboveViewTestHarness {
	@Rule
	public ActivityTestRule<StubControlsAboveViewTestHarness> rule =
			new ActivityTestRule<>(StubControlsAboveViewTestHarness.class);

	private StubControlsAboveViewTestHarness activity;

	@Before
	public void setup() {
		activity = rule.getActivity();
	}
}
