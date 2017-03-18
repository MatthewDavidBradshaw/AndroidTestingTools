package com.matthewtamlin.android_testing_tools.testing;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsAboveViewTestHarness;
import static org.mockito.Mockito.mock;


public class StubControlsAboveViewTestHarness extends ControlsAboveViewTestHarness<View> {
	private View testView;

	@Override
	public View getTestView() {
		if (testView == null) {
			testView = mock(View.class);
		}

		return testView;
	}
}