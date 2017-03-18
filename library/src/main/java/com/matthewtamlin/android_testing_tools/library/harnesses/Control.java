package com.matthewtamlin.android_testing_tools.library.harnesses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method which returns a control view for a TestHarness. This annotation must
 * only be applied to methods which are:
 * <ul> <li>Public</li> <li>Have no arguments</li> <li>Return a non-null View.</li></ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Control {
	/**
	 * @return the index of the control
	 */
	int value();
}
