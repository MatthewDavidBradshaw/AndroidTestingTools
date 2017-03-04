package com.matthewtamlin.android_testing_tools.library.espresso;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static com.matthewtamlin.android_testing_tools.library.espresso.CustomMatchers.isExactType;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * A ViewAction with built in type safety.
 * <p>
 * Subclasses can define the action to perform by implementing {@link #typeSafePerform(UiController,
 * View)}. If subclasses cannot avoid overriding {@link #getConstraints()} or {@link
 * #perform(UiController, View)}, then the superclass implementations must be called from within the
 * respective overrides.
 *
 * @param <T>
 * 		the type of View this action can be performed on
 */
public abstract class TypeSafeViewAction<T extends View> implements ViewAction {
	/**
	 * The class this action can be applied to.
	 */
	private final Class<T> targetClass;

	/**
	 * Whether or not this action can also be applied to subclasses of the target class.
	 */
	private final boolean allowSubclasses;

	/**
	 * Constructs a new TypeSafeViewAction.
	 *
	 * @param targetClass
	 * 		the class this view action can be applied to
	 * @param allowSubclasses
	 * 		whether or not this action can also be applied to subclasses of the target class
	 * @throws IllegalArgumentException
	 * 		if {@code targetClass} is null
	 */
	public TypeSafeViewAction(final Class<T> targetClass, final boolean allowSubclasses) {
		this.targetClass = checkNotNull(targetClass, "targetClass cannot be null.");
		this.allowSubclasses = allowSubclasses;
	}

	/**
	 * @return the class this view action can be applied to, not null
	 */
	public Class<T> getTargetClass() {
		return targetClass;
	}

	/**
	 * @return true if this action can be applied to subclasses of the target class, false otherwise
	 */
	public boolean allowsSubclasses() {
		return allowSubclasses;
	}

	@Override
	public Matcher<View> getConstraints() {
		if (allowSubclasses) {
			return isAssignableFrom(targetClass);
		} else {
			return isExactType(targetClass);
		}
	}

	@Override
	public void perform(final UiController uiController, final View view) {
		if (!getConstraints().matches(view)) {
			throw new RuntimeException("View supplied to perform did not satisfy getConstraints.");
		}

		//noinspection unchecked - This is prevented by the above check
		typeSafePerform(uiController, (T) view);
	}

	/**
	 * Performs this action on the given view.
	 *
	 * @param uiController
	 * 		the controller to use to interact with the UI, not null
	 * @param view
	 * 		the view to act upon, not null
	 */
	public abstract void typeSafePerform(UiController uiController, T view);
}