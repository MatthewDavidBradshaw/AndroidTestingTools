# AndroidTestingTools
A library of tools to make Android testing easier, with a focus on testing custom views. 

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-testing-tools:2.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidTestingTools).

## Compatibility
This library is compatible with Android 12 and up. Android lint may warn about an InvalidPackage exception, however that shouldn't matter if the library is only used in testing. The warning can be suppressed by adding the following to the android scope in the relevant build.gradle file:
```
lintOptions {
	disable 'InvalidPackage'
}
 ```
