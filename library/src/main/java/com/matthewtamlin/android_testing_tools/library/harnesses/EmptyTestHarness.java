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

import android.view.View;

/**
 * A TestHarness which displays controls only and no test view. The controls are defined by
 * annotating methods with {@link Control}. The annotation must only be applied to views which are:
 * <ul> <li>Public</li> <li>Have no arguments</li> <li>Return a View or View subclass</li>
 * <li>Never return null</li></ul>
 */
public class EmptyTestHarness extends ControlsOverViewTestHarness<View> {
	@Override
	public View getTestView() {
		return new View(this);
	}
}