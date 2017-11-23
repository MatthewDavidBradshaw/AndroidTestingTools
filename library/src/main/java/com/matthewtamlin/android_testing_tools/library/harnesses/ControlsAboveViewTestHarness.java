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

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.matthewtamlin.android_testing_tools.library.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.matthewtamlin.android_testing_tools.library.R.id.toggle_controls_visibility_button;
import static com.matthewtamlin.android_testing_tools.library.R.id.inner_controls_container;
import static com.matthewtamlin.android_testing_tools.library.R.id.outer_controls_container;
import static com.matthewtamlin.android_testing_tools.library.R.id.root;
import static com.matthewtamlin.android_testing_tools.library.R.id.test_view_container;

/**
 * A test harness which displays the controls above the test view.
 *
 * @param <T>
 * 		the type of view being tested
 */
public abstract class ControlsAboveViewTestHarness<T>
		extends AppCompatActivity
		implements TestHarness<T, FrameLayout, LinearLayout, LinearLayout, LinearLayout> {

	private final List<View> controls = new ArrayList<>();

	private LinearLayout rootView;

	private View statusBarSpacer;

	private LinearLayout innerControlsContainer;

	private LinearLayout outerControlsContainer;

	private FrameLayout testViewContainer;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controlsaboveview);

		rootView = findViewById(root);
		statusBarSpacer = findViewById(R.id.status_bar_spacer);
		innerControlsContainer = findViewById(inner_controls_container);
		outerControlsContainer = findViewById(outer_controls_container);
		testViewContainer = findViewById(test_view_container);

		findViewById(toggle_controls_visibility_button)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(final View v) {
						final int currentVis = innerControlsContainer.getVisibility();
						final int newVis = currentVis == VISIBLE ? GONE : VISIBLE;
						innerControlsContainer.setVisibility(newVis);
					}
				});

		getWindow()
				.getDecorView()
				.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

		drawBehindStatusBar(false);

		getTestViewContainer().addView(coerceToView(getTestView()));

		ControlBinder.bindControls(this);
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
	public View coerceToView(final T testView) {
		try {
			return (View) testView;
		} catch (final ClassCastException e) {
			throw new RuntimeException("The test view is not a View subclass. You must override coerceToView(T).");
		}
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

	@Override
	public void drawBehindStatusBar(final boolean enable) {
		statusBarSpacer.getLayoutParams().height = enable ? 0 : StatusBarHeightHelper.getStatusBarHeight(this);
		statusBarSpacer.invalidate();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(enable ? Color.TRANSPARENT : Color.BLACK);
		}
	}
}