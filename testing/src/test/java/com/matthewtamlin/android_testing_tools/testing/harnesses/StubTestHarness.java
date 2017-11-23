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

package com.matthewtamlin.android_testing_tools.testing.harnesses;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.TestHarness;

import java.util.ArrayList;
import java.util.List;

/**
 * A stub implementation of the TestHarness interface for use in testing.
 */
public class StubTestHarness implements TestHarness {
	private ArrayList<View> controls = new ArrayList<>();

	@Override
	public Object getRootView() {
		return null;
	}

	@Override
	public Object getInnerControlsContainer() {
		return null;
	}

	@Override
	public Object getOuterControlsContainer() {
		return null;
	}

	@Override
	public Object getTestView() {
		return null;
	}

	@Override
	public Object getTestViewContainer() {
		return null;
	}

	@Override
	public void enableControls(final boolean enable) {

	}

	@Override
	public void addControl(final View control) {
		controls.add(control);
	}

	@Override
	public void removeControl(final View control) {

	}

	@Override
	public List<View> getControls() {
		return controls;
	}

	@Override
	public void drawBehindStatusBar(final boolean enable) {

	}
}