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

package com.matthewtamlin.android_testing_tools.library.harnesses;

import android.view.View;

import java.util.List;

/**
 * An activity which hosts a view to be tested, as well as controls for interacting with the view.
 *
 * @param <T>
 * 		the type of view being tested
 * @param <C>
 * 		the type of view hosting the test view
 * @param <R>
 * 		the type of view used for the layout root
 * @param <I>
 * 		the type of view containing the controls
 * @param <O>
 * 		the type of view containing the controls a button for hiding/showing the controls
 */
public interface TestHarness<T, C, R, I, O> {
	/**
	 * @return the root view of this Activity's layout, not null
	 */
	public R getRootView();

	/**
	 * @return the view which contains the controls, not null
	 */
	public I getInnerControlsContainer();

	public O getOuterControlsContainer();

	/**
	 * @return the view under test, not null
	 */
	public T getTestView();

	/**
	 * @return the view which contains the test view
	 */
	public C getTestViewContainer();

	/**
	 * Allows the controls to be entirely hidden and disabled. This includes the button for
	 * temporarily hiding and showing the controls.
	 *
	 * @param enable
	 * 		true to enable the controls, false to disable then
	 */
	public void enableControls(boolean enable);

	public void addControl(View control);

	public void removeControl(View control);

	public List<View> getControls();
}