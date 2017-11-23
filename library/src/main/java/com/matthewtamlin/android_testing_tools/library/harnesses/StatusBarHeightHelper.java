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

package com.matthewtamlin.android_testing_tools.library.harnesses;

import android.content.Context;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Utility for setting the height of a view to match the status bar.
 */
public class StatusBarHeightHelper {
	public static int getStatusBarHeight(final Context context) {
		checkNotNull("Argument \'view\' cannot be null.");

		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

		if (resourceId > 0) {
			return context.getResources().getDimensionPixelSize(resourceId);
		} else {
			throw new RuntimeException("Could not get status bar height from resources.");
		}
	}
}