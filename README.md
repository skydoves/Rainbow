# Rainbow

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=16"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://travis-ci.org/skydoves/Rainbow"><img alt="BuildStatus" src="https://travis-ci.org/skydoves/Rainbow.svg?branch=master"/></a>
   <a href="https://androidweekly.net/issues/issue-398"><img alt="Android Weekly" src="https://img.shields.io/badge/Android%20Weekly-%23398-orange"/></a>
  <a href="https://us12.campaign-archive.com/?u=f39692e245b94f7fb693b6d82&id=febdf46615"><img alt="KotlinWeekly" src="https://img.shields.io/badge/KotlinWeekly-%23165-4E71E6"/></a>
  <a href="https://skydoves.github.io/libraries/rainbow/javadoc/rainbow/com.skydoves.rainbow/index.html"><img alt="Javadoc" src="https://img.shields.io/badge/Javadoc-Rainbow-yellow"/></a>
</p>

<p align="center">
🌈 An easy way to apply gradations and tinting for Android.
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/63633474-2a597800-c684-11e9-8aa3-db99575c73e0.png" width="694" height="235"/>
</p>

## Download
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/rainbow.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.skydoves%22%20AND%20a:%22rainbow%22)
[![Jitpack](https://jitpack.io/v/skydoves/Rainbow.svg)](https://jitpack.io/#skydoves/Rainbow)
#### Gradle
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        mavenCentral()
    }
}
```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:rainbow:1.0.3"
}
```

## SNAPSHOT 
[![Balloon](https://img.shields.io/static/v1?label=snapshot&message=balloon&logo=apache%20maven&color=C71A36)](https://oss.sonatype.org/content/repositories/snapshots/com/github/skydoves/rainbow/) <br>
Snapshots of the current development version of Balloon are available, which track [the latest versions](https://oss.sonatype.org/content/repositories/snapshots/com/github/skydoves/rainbow/).
```Gradle
repositories {
   maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
}
```

## Usage
We can apply gradations and tinting to any views easily using `Rainbow` class.

### Palette
Palette lambda expression collects colors for creating gradation.<br>
We can collect colors using `contextColor` and `color` functions.<br>
`contextColor` gets a __resource__ color from your `colors.xml` file, and `color` gets a __ColorInt__ color.<br>
They should be used with `+` operator in the palette lambda expression.

```kotlin
Rainbow(myCardView).palette { // constructs a palette for collecting colors.
  +contextColor(R.color.red_200) // getting a color from the resource
  +contextColor(R.color.yellow_200)
  +contextColor(R.color.green_200)
  +contextColor(R.color.blue_200)
  +color(Color.WHITE) // getting a color
}.withAlpha(225) // sets alpha (0~255)
 .foreground() // applies gradations to myCardView
```

Here is a kotlin-extesion way to apply gradations using `View.rainbow()` method to views.

```kotlin
myLinearLayout.rainbow().palette {
  +contextColor(R.color.skyBlue)
  +contextColor(R.color.colorPrimary)
}.background(orientation = RainbowOrientation.TOP_BOTTOM, radius = 8)
```

<img src="https://user-images.githubusercontent.com/24237865/63633706-6c37ed80-c687-11e9-9316-b29530c7f1a8.jpg" align="right" width="32%"/>

### Background, Foreground
We can apply gradations composed with palette colors to the view's background or foreground.<br>
The `forground()` method can be applied to your `CardView` or something others.

```kotlin
Rainbow(myCardView).palette {
  +contextColor(R.color.red_200)
  +contextColor(R.color.yellow_200)
}.background() or .foreground()
```

And we can control the gradient orientation and corner radius. <br>
We can use 8 kinds of orientation which `RainbowOrientation`.
```kotlin
background(orientation = RainbowOrientation.RIGHT_LEFT, radius = 8)
background(orientation = RainbowOrientation.TOP_BOTTOM, radius = 8)
foreground(RainbowOrientation.DIAGONAL_TOP_LEFT, 8)
foreground(RainbowOrientation.DIAGONAL_BOTTOM_RIGHT, 8)
```

### Tinting
We can change some kinds of view's tint colors which can be applied tint.<br>
Here are views can be applied tint: TextView(drawable), ImageView, [CompoundButton](https://developer.android.com/reference/android/widget/CompoundButton), [TintableBackgroundView](https://developer.android.com/reference/androidx/core/view/TintableBackgroundView).

```kotlin
Rainbow(myCheckBox).palette {
  +contextColor(R.color.red_200)
}.tint()
```

### Drawable
We can get a `GradientDrawable` using `getDrawable` method. <br>

```kotlin
val drawable = Rainbow(myCheckBox).palette {
  +contextColor(R.color.red_200)
  +contextColor(R.color.yellow_200)
}.getDrawable()
```

### RainbowView
RainbowView is a gradient view for implementing gradations. <br><br>
Add following XML namespace inside your XML layout file.

```gradle
xmlns:app="http://schemas.android.com/apk/res-auto"
```

<img src="https://user-images.githubusercontent.com/24237865/63634479-f8e7a900-c691-11e9-99b7-c40651a593f0.gif" align="right" width="33%"/>

#### RainbowView in xml layout
```gradle
<com.skydoves.rainbow.RainbowView
  android:id="@+id/rainbow"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:rainbowView_colors="@array/colors" // colors for gradient.
  app:rainbowView_orientation="left_right" // gradient orientation.
  app:rainbowView_radius="12dp" // corner radius.
/>
```

The `rainbowView_colors` attributes gets color list from the color-array from your `colors.xml`.

```gradle
<resources>
  <color name="colorPrimary">#C51162</color>
  ...
  <array name="colors">
    <item>@color/red_100</item>
    <item>@color/orange_100</item>
    <item>@color/yellow_100</item>
    <item>@color/green_100</item>
    ...
  </array>
</resources>
```

### BinaryRainbowView
BinaryRainbowView is a gradient view for implementing a simple view with gradations.

```gradle
<com.skydoves.rainbow.BinaryRainbowView
  android:layout_width="match_parent"
  android:layout_height="80dp"
  app:binaryRainbowView_startColor="@color/md_green_100" // starting color of the gradient.
  app:binaryRainbowView_centerColor="@color/white" // center color of the gradient.
  app:binaryRainbowView_endColor="@color/skyBlue" // end color of the gradient.
  app:binaryRainbowView_orientation="bottom_top" // gradient orientation.
  app:binaryRainbowView_radius="12dp" // corner radius
/>
```

### Shuffle
`RainbowView` and `BinaryRainbowView` provides shuffling the palette colors using `shuffleColors()` method. The gradation colors placement will be changed randomly.
```kotlin
rainbow.shuffleColors()
```

### Usage in Java
Here are some usages for Java developers.

```java
new Rainbow(myView)
    .addContextColor(R.color.red_100)
    .addContextColor(R.color.orange_100)
    .addContextColor(R.color.yellow_100)
    .addContextColor(R.color.green_100)
    .withAlpha(255)
    .background(RainbowOrientation.RIGHT_LEFT, 8);
```

## Find this library useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/Rainbow/stargazers)__ for this repository. :star:

# License
```xml
Copyright 2019 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
