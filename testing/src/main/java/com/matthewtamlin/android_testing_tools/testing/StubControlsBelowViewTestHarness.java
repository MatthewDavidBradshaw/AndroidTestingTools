package com.matthewtamlin.android_testing_tools.testing;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.Control;
import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsAboveViewTestHarness;
import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsBelowViewTestHarness;

/**
 * An implementation of the ControlsBelowViewTestHarness for use in testing.
 */
public class StubControlsBelowViewTestHarness extends ControlsBelowViewTestHarness<View> {
	public View testView;

	public View controlView1;

	public View controlView2;

	public View controlView3;

	@Override
	public View getTestView() {
		if (testView == null) {
			testView = new View(this);
		}

		return testView;
	}

	@Control(3)
	public View getControl3() {
		if (controlView3 == null) {
			controlView3 = new View(this);
		}

		return controlView3;
	}

	@Control(1)
	public View getControl1() {
		if (controlView1 == null) {
			controlView1 = new View(this);
		}

		return controlView1;
	}

	@Control(2)
	public View getControl2() {
		if (controlView2 == null) {
			controlView2 = new View(this);
		}

		return controlView2;
	}
}