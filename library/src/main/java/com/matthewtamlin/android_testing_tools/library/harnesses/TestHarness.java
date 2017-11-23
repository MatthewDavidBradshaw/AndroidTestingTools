/*
 * Copyright 2016-2017 Matthew Tamlin
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

import java.util.List;

/**
 * Displays a view and a series of controls for interacting with the view.
 *
 * @param <T>
 * 		the type of the view being tested
 * @param <C>
 * 		the type of the view hosting the test view
 * @param <R>
 * 		the type of the root view
 * @param <I>
 * 		the type of the view containing the controls
 * @param <O>
 * 		the type of the view containing the control container along with a button for hiding and showing the controls
 */
public interface TestHarness<T, C, R, I, O> {
	/**
	 * @return the root view, not null
	 */
	public R getRootView();

	/**
	 * @return the view containing the controls, not null
	 */
	public I getInnerControlsContainer();

	/**
	 * @return the view containing the controls and the button for toggling the visibility of the controls
	 */
	public O getOuterControlsContainer();

	/**
	 * @return the view under test, not null
	 */
	public T getTestView();

	/**
	 * @return the view hosting the test view
	 */
	public C getTestViewContainer();

	/**
	 * Supplies the test view in a form which can be added to a view hierarchy. Override this method if the declared
	 * type of the test view does not explicitly extend the {@link View} class, but the runtime type does.
	 *
	 * @param testView
	 * 		the view under test, not null
	 *
	 * @return the supplied test view, as a subclass of {@link View}, not null
	 */
	public View coerceToView(T testView);

	/**
	 * Entirely disables or enables the controls. The controls are not shown when disabled.
	 *
	 * @param enable
	 * 		true to enable the controls, false to disable them
	 */
	public void enableControls(boolean enable);

	/**
	 * Adds the supplied control to the controls container.
	 *
	 * @param control
	 * 		the control to add, not null
	 */
	public void addControl(View control);

	/**
	 * Removes the supplied control from the controls container.
	 *
	 * @param control
	 * 		the control to remove, not null
	 */
	public void removeControl(View control);

	/**
	 * A list containing all controls. The view might not be modifiable.
	 *
	 * @return the list, not null
	 */
	public List<View> getControls();

	/**
	 * Sets whether or not the view should be drawn behind the status bar.
	 *
	 * @param enable
	 * 		true to allow drawing behind the status bar, false to disallow it
	 */
	public void drawBehindStatusBar(boolean enable);
}