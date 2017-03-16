package com.matthewtamlin.android_testing_tools.testing.harnesses;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.Control;
import com.matthewtamlin.android_testing_tools.library.harnesses.ControlBinder;
import com.matthewtamlin.android_testing_tools.library.harnesses.TestHarness;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class TestControlBinder {
	@Test(expected = IllegalArgumentException.class)
	public void testBindControls_nullSupplied() {
		ControlBinder.bindControls(null);
	}

	@Test
	public void testBindControls_annotationOnMethodWithNonViewReturn() {
		final StubTestHarness testHarness = new StubTestHarness();

		ControlBinder.bindControls(testHarness);

		assertThat(testHarness.controls, is(new ArrayList<View>()));
	}

	@Test(expected = InvocationTargetException.class)
	public void testBindControls_annotationOnMethodWithArguments() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			public View someMethod(final Object argument) {
				return mock(View.class);
			}
		};

		ControlBinder.bindControls(testHarness);
	}

	@Test(expected = IllegalAccessException.class)
	public void testBindControls_annotationOnMethodWithProtectedAccess() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			protected View someMethod() {
				return mock(View.class);
			}
		};

		ControlBinder.bindControls(testHarness);
	}

	@Test
	public void testBindControls_annotationOnMethodWithDefaultAccess() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			View someMethod() {
				return mock(View.class);
			}
		};

		ControlBinder.bindControls(testHarness);
	}

	@Test
	public void testBindControls_annotationOnMethodWithPrivateAccess() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			private View someMethod() {
				return mock(View.class);
			}
		};

		ControlBinder.bindControls(testHarness);
	}

	@Test
	public void testBindControls_annotationOnValidMethods() {
		
	}

	@Test
	public void testBindControls_noAnnotations() {
		final StubTestHarness testHarness = new StubTestHarness();

		ControlBinder.bindControls(testHarness);

		assertThat(testHarness.controls, is(new ArrayList<View>()));
	}

	public class StubTestHarness implements TestHarness {
		private ArrayList<View> controls = new ArrayList<>();

		@Override
		public Object getRootView() {
			return null;
		}

		@Override
		public Object getInnerControlsContainer() {
			return null;
		}

		@Override
		public Object getOuterControlsContainer() {
			return null;
		}

		@Override
		public Object getTestView() {
			return null;
		}

		@Override
		public Object getTestViewContainer() {
			return null;
		}

		@Override
		public void enableControls(final boolean enable) {

		}

		@Override
		public void addControl(final View control) {
			controls.add(control);
		}

		@Override
		public void removeControl(final View control) {

		}

		@Override
		public List<View> getControls() {
			return null;
		}
	}
}
