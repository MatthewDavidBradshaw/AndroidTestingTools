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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_innerControls;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_hideShowControlsButton;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_outerControls;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_root;
import static com.matthewtamlin.android_testing_tools.library.R.id.controlsAboveView_testViewContainer;

/**
 * A TestHarness which displays control buttons above the test view.
 *
 * @param <T>
 * 		the type of view being tested
 */
public abstract class ControlsAboveViewTestHarness<T>
		extends TestHarness<T, FrameLayout, LinearLayout, LinearLayout, LinearLayout> {
	private final List<View> controls = new ArrayList<>();

	private LinearLayout rootView;

	private LinearLayout innerControlsContainer;

	private LinearLayout outerControlsContainer;

	private FrameLayout testViewContainer;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controlsaboveview);

		rootView = (LinearLayout) findViewById(controlsAboveView_root);
		innerControlsContainer = (LinearLayout) findViewById(controlsAboveView_innerControls);
		outerControlsContainer = (LinearLayout) findViewById(controlsAboveView_outerControls);
		testViewContainer = (FrameLayout) findViewById(controlsAboveView_testViewContainer);

		findViewById(controlsAboveView_hideShowControlsButton)
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
	public LinearLayout getOuterControlsContainer() {
		return outerControlsContainer;
	}

	@Override
	public FrameLayout getTestViewContainer() {
		return testViewContainer;
	}

	@Override
	public void enableControls(final boolean enable) {
		outerControlsContainer.setVisibility(enable ? VISIBLE : GONE);
	}

	@Override
	public void addControl(final View control) {
		getInnerControlsContainer().addView(control);
		controls.add(control);
	}

	@Override
	public void removeControl(final View control) {
		getInnerControlsContainer().removeView(control);
		controls.remove(control);
	}

	@Override
	public List<View> getControls() {
		return Collections.unmodifiableList(controls);
	}
}