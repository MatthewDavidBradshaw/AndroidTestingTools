package com.matthewtamlin.android_testing_tools.testing;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.Control;
import com.matthewtamlin.android_testing_tools.library.harnesses.EmptyTestHarness;

/**
 * An implementation of the ControlsAboveViewTestHarness for use in testing.
 */
public class StubEmptyTestHarness extends EmptyTestHarness {
	public View controlView1;

	public View controlView2;

	public View controlView3;

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