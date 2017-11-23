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