package com.matthewtamlin.android_testing_tools.library.espresso;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

public abstract class TypeSafeViewAssertion<T extends View> implements ViewAssertion {
	private final Class<T> clazz;

	public TypeSafeViewAssertion(Class<T> clazz) {
		this.clazz = checkNotNull(clazz, "clazz cannot be null.");
	}

	@Override
	public void check(final View view, final NoMatchingViewException noViewFoundException) {
		if (view == null) {
			throw noViewFoundException;
		}

		if (!isAssignableFrom(clazz).matches(view)) {
			throw new IllegalArgumentException("view must be an instance of or subclass of " +
					clazz.getName());
		}

		//noinspection unchecked - This is prevented by the above check
		typeSafeCheck((T) view);
	}

	public abstract void typeSafeCheck(T view);
}