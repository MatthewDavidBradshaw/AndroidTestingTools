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