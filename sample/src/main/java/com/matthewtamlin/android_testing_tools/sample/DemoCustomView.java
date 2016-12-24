package com.matthewtamlin.android_testing_tools.sample;

import android.content.Context;
import android.widget.TextView;

/**
 * A simple custom TextView subclass. This could easily be unit tested without using the UI, but for
 * the purposes of the demonstration we will overlook this.
 */
public class DemoCustomView extends TextView {
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