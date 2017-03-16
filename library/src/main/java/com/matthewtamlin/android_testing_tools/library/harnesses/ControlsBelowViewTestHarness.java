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

package com.matthewtamlin.android_testing_tools.library.harnesses;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.matthewtamlin.android_testing_tools.library.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsBelowView_controlsContainer;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsBelowView_hideShowControlsButton;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsBelowView_outerControlsContainer;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsBelowView_root;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsBelowView_testViewContainer;

/**
 * A TestHarness which displays control buttons below the test view.
 *
 * @param <T>
 * 		the type of view being tested
 */
public abstract class ControlsBelowViewTestHarness<T>
		extends TestHarness<T, FrameLayout, LinearLayout, LinearLayout, LinearLayout> {
	private LinearLayout rootView;

	private LinearLayout innerControlsContainer;

	private LinearLayout outerControlsContainer;

	private FrameLayout testViewContainer;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controlsbelowview);

		rootView = (LinearLayout) findViewById(
				controlsBelowView_root);
		innerControlsContainer = (LinearLayout) findViewById(
				controlsBelowView_controlsContainer);
		outerControlsContainer = (LinearLayout) findViewById(
				controlsBelowView_outerControlsContainer);
		testViewContainer = (FrameLayout) findViewById(
				controlsBelowView_testViewContainer);

		findViewById(controlsBelowView_hideShowControlsButton)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(final View v) {
						final int currentVis = innerControlsContainer.getVisibility();
						final int newVis = currentVis == VISIBLE ? GONE : VISIBLE;
						innerControlsContainer.setVisibility(newVis);
					}
				});



		getTestViewContainer().addView((View) getTestView());
	}

	@Override
	public LinearLayout getRootView() {
		return rootView;
	}

	@Override
	public LinearLayout getInnerControlsContainer() {
		return innerControlsContainer;
	}

	@Override
	public FrameLayout getTestViewContainer() {
		return testViewContainer;
	}

	@Override
	public void enableControls(final boolean enable) {
		final LinearLayout outerControlsContainer = (LinearLayout) findViewById(
				controlsBelowView_outerControlsContainer);

		outerControlsContainer.setVisibility(enable ? VISIBLE : GONE);
	}
}