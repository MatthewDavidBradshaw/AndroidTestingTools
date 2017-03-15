package com.matthewtamlin.android_testing_tools.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.matthewtamlin.android_testing_tools.library.harnesses.ControlsOverViewTestHarness;

/**
 * Displays a DemoCustomView and some control buttons.
 */
public class DemoTestHarness extends ControlsOverViewTestHarness<DemoCustomView> {
	private DemoCustomView testView;

	@Override
	protected void onCreate(final @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getInnerControlsContainer().addView(createControlButtonToSetText());
	}

	@Override
	public DemoCustomView getTestView() {
		if (testView == null) {
			testView = new DemoCustomView(this);
		}

		return testView;
	}

	private Button createControlButtonToSetText() {
		final Button b = new Button(this);
		b.setText("Set text \"Christmas\"");
		b.setAllCaps(false);

		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				getTestView().setText("Christmas");
			}
		});

		return b;
	}
}