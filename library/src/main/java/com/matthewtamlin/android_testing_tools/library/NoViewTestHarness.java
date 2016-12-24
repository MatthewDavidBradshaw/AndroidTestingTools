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

package com.matthewtamlin.android_testing_tools.library;

import android.annotation.SuppressLint;
import android.view.View;

/**
 * A TestHarness which displays no view. Useful when an Activity is needed in testing, but no view
 * is required.
 */
@SuppressLint("Registered") // Class is part of a public API and is not instantiated in this project
public class NoViewTestHarness extends ControlsOverViewTestHarness<View> {
	@Override
	public View getTestView() {
		return new View(this);
	}
}