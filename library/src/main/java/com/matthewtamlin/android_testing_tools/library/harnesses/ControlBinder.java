package com.matthewtamlin.android_testing_tools.library.harnesses;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

public class ControlBinder {
	public static void bindControls(final TestHarness<?, ?, ?, ?, ?> testHarness) {
		checkNotNull(testHarness, "testHarness cannot be null.");

		final Map<Integer, View> controls = new TreeMap<>();

		for (final Method m : testHarness.getClass().getMethods()) {
			if (m.isAnnotationPresent(Control.class)) {
				try {
					final View control = (View) m.invoke(testHarness);

					final Control controlAnnotation = m.getAnnotation(Control.class);
					final int index = controlAnnotation.value();

					if (controls.keySet().contains(index)) {
						throw new RuntimeException("All @Control methods in a single class must " +
								"have unique values.");
					}

					controls.put(index, control);
				} catch (final IllegalAccessException e) {
					throw new RuntimeException("@Control an only be applied to a method if it is " +
							"public, accepts no arguments, and returns a View.");
				} catch (final InvocationTargetException e) {
					throw new RuntimeException("@Control an only be applied to a method if it is " +
							"public, accepts no arguments, and returns a View.");
				}
			}
		}

		for (final Integer index : controls.keySet()) {
			testHarness.getInnerControlsContainer().addView(controls.get(index));
		}
	}
}
