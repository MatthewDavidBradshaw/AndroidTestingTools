# AndroidTestingTools
A library of tools to make Android testing easier, with a focus on testing custom views. 

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-testing-tools:2.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidTestingTools).

## Usage
A test harness is an activity which displays a view, as well as a series of controls for interacting with the view. Test harnesses facilitate both manual and automated testing by allowing the user to interact with the view, and providing a target for espresso tests.

A test harness can be created in three steps:
1. Subclass one of the provided test harnesses.
2. Define the test view by overriding getTestView().
3. Define the controls by annotation methods with @Control.

For example:
```java
public class MyTestHarness extends ControlsAboveViewTestHarness<MyCustomView> {
	private MyCustomView testView;
	
	@Override
	public MyCustomView getTestView() {
		if (testView == null) {
			testView = new MyCustomView(this);
		}
		
		return testView();
	}
	
	@Control(1)
	public Button doSomething() {
		Button b = new Button(this);
		b.setText("Do something");
		
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getTestView().doSomething();
			}
		});
		
		return b;
	}
	
	@Control(2)
	public Button doSomethingElse() {
		Button b = new Button(this);
		b.setText("Do something else");
		
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getTestView().doSomethingElse();
			}
		});
		
		return b;
	}
}
```

Four test harnesses are provided:
- ControlsAboveViewTestHarness: The controls are positioned vertically above the test view.
- ControlsBelowViewTestHarness: The controls are positioned vertically below the test view.
- ControlsOverViewTestHarness: The controls are stacked on top of the test view.
- EmptyTestHarness: Displays controls but no test view. Useful when an activity is needed in an automated test.


## Compatibility
This library is compatible with Android 12 and up. Android lint may warn about an InvalidPackage exception, however that shouldn't matter if the library is only used in testing. The warning can be suppressed by adding the following to the android scope in the relevant build.gradle file:
```
lintOptions {
	disable 'InvalidPackage'
}
 ```
