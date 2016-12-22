# AndroidTestingTools
A library of tools to make Android testing easier. Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-testing-tools:1.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidTestingTools).

## Test Harnesses
A test harness is an Activity which hosts a test view and a set of controls. This allows a user to directly interact with a view/layout, which is especially useful when designing custom UI components. Furthermore, a test harness can be used as an ActivityTestRule in an instrumented test to allow unit testing of custom UI components. This library provides an abstract base test harness, and a few implementations. The implementations allow the controls to be positioned above, below and on top of the test view.

## EspressoHelper
The EspressoHelper class provides a static method for converting direct View references to Espresso ViewInterators. This is useful when a View needs to be used with the Espresso framework, but it is only obtainable as a direct View object.

## Compatibility
This library is compatible with Android 12 and up.
