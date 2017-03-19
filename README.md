# AndroidTestingTools
A library of tools to make Android testing easier, with a focus on testing custom views. 

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-testing-tools:2.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidTestingTools).

## Test Harnesses
A test harness is an Activity which displays a view along with a series of controls. While the user can interact with the view as they normally would, they can also use the controls to invoke the methods of the view directly. This can be used to easily and quickly test the look, feel and performance of a custom UI component. Test harnesses can also be used to unit test custom views. By declaring a test harness as an ActivityTestRule in an instrumented test class, the test cases can get a direct reference to the view under test. Coupled with the EspressoHelper class, the view can then be used with the Espresso framework. This library provides an abstract TestHarness class, as well as several subclasses which vary the layout of the activity. The [sample](sample/src/main/java/com/matthewtamlin/android_testing_tools/sample/DemoTestHarness.java) contains an example of a test harness in use.


## EspressoHelper
The [EspressoHelper](library/src/main/java/com/matthewtamlin/android_testing_tools/library/EspressoHelper.java) class provides static methods for converting View objects to ViewInteractor objects. This is useful when a View needs to be used with the Espresso framework, but it is only obtainable as a direct View object. The [sample](sample/src/androidTest/java/com/matthewtamlin/android_testing_tools/sample/EspressoTestDemoCustomView.java) contains an example of the EspressoHelper in use.

## Compatibility
This library is compatible with Android 12 and up. Android lint may warn about an InvalidPackage exception, however that shouldn't matter if the library is only used in testing. The warning can be suppressed by adding the following to the android scope in the relevant build.gradle file:
```
lintOptions {
	disable 'InvalidPackage'
}
 ```
