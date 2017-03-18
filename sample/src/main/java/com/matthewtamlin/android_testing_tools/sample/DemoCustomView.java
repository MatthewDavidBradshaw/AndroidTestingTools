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

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * A simple custom TextView subclass. This could easily be unit tested without using the UI, but for
 * the purposes of the demonstration we will overlook this.
 */
public class DemoCustomView extends View {
	/**
	 * Constructs a new DemoCustomView.
	 *
	 * @param context
	 * 		the Context the view is operating in, not null
	 */
	public DemoCustomView(final Context context) {
		super(context);
	}

	/**
	 * Checks that the view contains the supplied text.
	 *
	 * @param textToCheck
	 * 		some text
	 * @return true if the view currently contains the supplied text, false otherwise
	 */
	public boolean textContains(final String textToCheck) {
		final String textInView = (String) getText();
		return textInView.contains(textToCheck);
	}
}