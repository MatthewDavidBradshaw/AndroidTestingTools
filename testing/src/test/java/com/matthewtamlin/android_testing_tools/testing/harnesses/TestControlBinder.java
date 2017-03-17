package com.matthewtamlin.android_testing_tools.testing.harnesses;

import android.view.View;

import com.matthewtamlin.android_testing_tools.library.harnesses.Control;
import com.matthewtamlin.android_testing_tools.library.harnesses.ControlBinder;
import com.matthewtamlin.android_testing_tools.testing.harnesses.stubs.StubTestHarness;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class TestControlBinder {
	@Test(expected = IllegalArgumentException.class)
	public void testBindControls_nullTestHarnessSupplied() {
		ControlBinder.bindControls(null);
	}

	@Test(expected = RuntimeException.class)
	public void testBindControls_annotationOnMethodWithNonViewReturnType() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			public Object someMethod() {
				return new Object();
			}
		};

		ControlBinder.bindControls(testHarness);

		assertThat(testHarness.getControls(), is((List) new ArrayList<View>()));
	}

	@Test(expected = RuntimeException.class)
	public void testBindControls_annotationOnMethodWithArguments() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			public View someMethod(final Object argument) {
				return mock(View.class);
			}
		};

		ControlBinder.bindControls(testHarness);
	}

	@Test(expected = RuntimeException.class)
	public void testBindControls_annotationOnMethodWithDefaultAccess() {
		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(1)
			View someMethod() {
				return mock(View.class);
			}
		};

		ControlBinder.bindControls(testHarness);
	}

	@Test(expected = RuntimeException.class)
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
		final View view1 = mock(View.class);
		final View view2 = mock(View.class);
		final View view3 = mock(View.class);

		final StubTestHarness testHarness = new StubTestHarness() {
			@Control(2)
			protected View someMethod2() {
				return view2;
			}

			@Control(1)
			public View someMethod1() {
				return view1;
			}

			@Control(3)
			protected View someMethod3() {
				return view3;
			}
		};

		ControlBinder.bindControls(testHarness);

		final List<View> expectedViews = new ArrayList<>();
		expectedViews.add(view1);
		expectedViews.add(view2);
		expectedViews.add(view3);

		assertThat(testHarness.getControls(), is(expectedViews));
	}

	@Test
	public void testBindControls_noAnnotations() {
		final StubTestHarness testHarness = new StubTestHarness();

		ControlBinder.bindControls(testHarness);

		assertThat(testHarness.getControls(), is((List) new ArrayList<View>()));
	}
}
