package com.matthewtamlin.android_testing_tools.testing;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsAboveViewTestHarness;


public class StubControlsAboveViewTestHarness extends ControlsAboveViewTestHarness<View> {
	public View testView;

	@Override
	public View getTestView() {
		if (testView == null) {
			testView = new View(this);
		}

		return testView;
	}
}