package com.matthewtamlin.android_testing_tools.library.espresso;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static com.matthewtamlin.android_testing_tools.library.espresso.CustomMatchers.isExactType;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * A ViewAssertion with built in type safety.
 * <p>
 * Subclasses can define the action to perform by implementing {@link #typeSafeCheck(View,
 * NoMatchingViewException)}. If subclasses cannot avoid overriding {@link #check(View,
 * NoMatchingViewException)}, then the superclass implementation must be called from within teh
 * override.
 *
 * @param <T>
 * 		the type of View this assertion can be performed on
 */
public abstract class TypeSafeViewAssertion<T extends View> implements ViewAssertion {
	/**
	 * The class this action can be applied to.
	 */
	private final Class<T> targetClass;

	/**
	 * Whether or not this action can also be applied to subclasses of the target class.
	 */
	private boolean allowSubclasses;

	/**
	 * Constructs a new TypeSafeViewAssertion.
	 *
	 * @param targetClass
	 * 		the class this view assertion can be applied to
	 * @param allowSubclasses
	 * 		whether or not this assertion can also be applied to subclasses of the target class
	 * @throws IllegalArgumentException
	 * 		if {@code targetClass} is null
	 */
	public TypeSafeViewAssertion(final Class<T> targetClass, final boolean allowSubclasses) {
		this.targetClass = checkNotNull(targetClass, "targetClass cannot be null.");
		this.allowSubclasses = allowSubclasses;
	}

	@Override
	public void check(final View view, final NoMatchingViewException noViewFoundException) {
		checkConstraints(view);

		//noinspection unchecked - This is prevented by the above check
		typeSafeCheck((T) view, noViewFoundException);
	}

	/**
	 * Checks the state of the given view (if such a view is present).
	 *
	 * @param view
	 * 		the view if one was found during the view interaction, or null if it was not
	 * @param noViewFoundException
	 * 		an exception detailing why the view could not be found, or null if the view was found
	 */
	public abstract void typeSafeCheck(T view, NoMatchingViewException noViewFoundException);

	/**
	 * Checks that the supplied view can be used with this view assertion. If the check fails then a
	 * detailed exception will be thrown. If the check passes, the method will exit normally.
	 *
	 * @param view
	 * 		the view to check
	 */
	private void checkConstraints(final View view) {
		if (view == null) {
			return;
		} else if (allowSubclasses) {
			if (!isAssignableFrom(targetClass).matches(view)) {
				throw new RuntimeException("view must be an instance of" + targetClass.getName());
			}
		} else {
			if (!isExactType(targetClass).matches(view)) {
				throw new RuntimeException("view must be an instance of " + targetClass.getName() +
						" (excluding subclasses)");
			}
		}
	}
}