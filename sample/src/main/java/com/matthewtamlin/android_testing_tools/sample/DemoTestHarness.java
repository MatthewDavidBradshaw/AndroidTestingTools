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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.matthewtamlin.android_testing_tools.library.harnesses.Control;
import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsOverViewTestHarness;

/**
 * Displays a DemoCustomView and some control buttons.
 */
public class DemoTestHarness extends ControlsOverViewTestHarness<DemoCustomView> {
	private DemoCustomView testView;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public DemoCustomView getTestView() {
		if (testView == null) {
			testView = new DemoCustomView(this);
		}

		return testView;
	}

	@Control(1)
	private Button doSomething() {
		final Button b = new Button(this);
		b.setText("Do something");
		b.setAllCaps(false);

		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				getTestView().doSomething();
			}
		});

		return b;
	}
}