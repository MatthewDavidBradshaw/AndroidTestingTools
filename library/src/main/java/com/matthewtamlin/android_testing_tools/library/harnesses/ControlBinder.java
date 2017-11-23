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

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Binds controls to test harnesses. This class is bound by the restrictions of reflection and may not be able to
 * bind all controls if test harnesses are subclassed.
 */
public class ControlBinder {
	/**
	 * Looks for {@link Control} annotated methods in the supplied test harness and binds the controls to the view.
	 *
	 * @param testHarness
	 * 		the test harness to bind the controls to, not null
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code testHarness} is null
	 * @throws RuntimeException
	 * 		if a method annotated with @Control returns null
	 * @throws RuntimeException
	 * 		if a method annotated with @Control has a return type which is not a view or a view subclass
	 * @throws RuntimeException
	 * 		if a method annotated with @Control cannot be accessed
	 * @throws RuntimeException
	 * 		if a method annotated with @Control has arguments
	 * @throws RuntimeException
	 * 		if a method annotation with @Control throws an exception when invoked
	 * @throws RuntimeException
	 * 		if two or more @Control annotations have the same order
	 */
	public static void bindControls(final TestHarness<?, ?, ?, ?, ?> testHarness) {
		checkNotNull(testHarness, "testHarness cannot be null.");

		final Map<Integer, View> controls = new TreeMap<>();

		for (final Method m : testHarness.getClass().getMethods()) {
			if (m.isAnnotationPresent(Control.class)) {
				View control;

				try {
					m.setAccessible(true);

					final Object result = m.invoke(testHarness);

					if (result == null) {
						throw new RuntimeException("A method annotated with @Control returned " +
								"null.");
					}

					if (!(result instanceof View)) {
						throw new RuntimeException("@Control was applied to a method with a " +
								"return type which is not assignable to android.view.View.");
					}

					control = (View) m.invoke(testHarness);
				} catch (final IllegalAccessException e) {
					throw new RuntimeException("Unable to access a method annotated with " +
							"@Control. The method must be public.", e);
				} catch (final InvocationTargetException e) {
					throw new RuntimeException("A method annotated with @Control threw an " +
							"exception when invoked.", e);
				} catch (final IllegalArgumentException e) {
					throw new RuntimeException("Unable to invoke a method annotated with " +
							"@Control. The method must accept no arguments.", e);
				}


				final Control annotation = m.getAnnotation(Control.class);
				final int controlIndex = annotation.value();

				if (controls.keySet().contains(controlIndex)) {
					throw new RuntimeException("All @Control annotations in a single class must " +
							"have unique values.");
				}

				controls.put(controlIndex, control);
			}
		}

		for (final Integer index : controls.keySet()) {
			testHarness.addControl(controls.get(index));
		}
	}
}
