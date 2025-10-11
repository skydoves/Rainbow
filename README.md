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
🌈 An expressive Kotlin DSL for dynamically creating and applying gradients, text shaders, and tints to any Android View.
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/63633474-2a597800-c684-11e9-8aa3-db99575c73e0.png" width="694" height="235"/>
</p>

## Download
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/rainbow.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.skydoves%22%20AND%20a:%22rainbow%22)
#### Gradle

Add the dependency below on your **root** `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("com.github.skydoves:rainbow:1.1.0")
}
```

## Usage Guide

The `Rainbow` class provides a fluent and expressive API for applying gradients and tints to Android `View` instances.

### Defining a Color Palette

A gradient is defined by a collection of colors, which we refer to as a **palette**. The `palette` lambda block is the primary mechanism for constructing this collection.

Within the lambda, you can add colors using the `contextColor` and `color` functions. The `contextColor` function resolves a color resource from your `colors.xml` file, whereas the `color` function accepts a `ColorInt` value directly. These functions should be used with the unary `+` operator to add them to the palette.

```kotlin
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.contextColor
import com.skydoves.rainbow.color

val rainbow = Rainbow(myCardView)
rainbow.palette {
  // Add a color from a resource file (e.g., res/values/colors.xml)
  +contextColor(R.color.red_200)
  +contextColor(R.color.yellow_200)
  
  // Add a ColorInt value directly
  +color(Color.WHITE)
}
```

#### Kotlin Extension for Fluent Syntax

For a more concise and idiomatic approach, the `View.rainbow()` extension function is provided. This allows you to chain palette creation and application directly onto any `View` instance.

```kotlin
import com.skydoves.rainbow.rainbow

myLinearLayout.rainbow().palette {
  +contextColor(R.color.skyBlue)
  +contextColor(R.color.colorPrimary)
}.background()
```

### Applying Gradients to Views

Once a palette is defined, you can apply the resulting gradient to a view's **background** or **foreground**.

```kotlin
// Applies the gradient to the view's background drawable.
rainbow.background()

// Applies the gradient as a foreground drawable.
// This is particularly effective for views like CardView.
rainbow.foreground()
```

The gradient's orientation and corner radius can be customized. There are eight predefined orientations available in the `RainbowOrientation` enum.

```kotlin
import com.skydoves.rainbow.RainbowOrientation

// Apply a background gradient from top to bottom with an 8dp corner radius.
myView.rainbow().palette { ... }.background(
  orientation = RainbowOrientation.TOP_BOTTOM,
  radius = 8
)

// Apply a foreground gradient diagonally with a 12dp corner radius.
myCardView.rainbow().palette { ... }.foreground(
  orientation = RainbowOrientation.DIAGONAL_TOP_LEFT,
  radius = 12
)
```

### Shading TextViews

For `TextView` instances, `Rainbow` provides a `shade` method that applies the gradient to the text itself, creating a multi-colored text effect.

```kotlin
myTextView.rainbow().palette {
  +contextColor(R.color.colorPrimary)
  +contextColor(R.color.md_orange_100)
  +contextColor(R.color.md_blue_200)
}.shade()
```

You can also define a palette from a color array resource declared in XML.

```kotlin
// In res/values/colors.xml:
// <array name="rainbow_colors">
//   <item>@color/red_100</item>
//   <item>@color/blue_100</item>
// </array>

myTextView.rainbow().palette {
  +colorArray(R.array.rainbow_colors)
}.shade()
```

### Tinting Views

The `tint` method applies the defined palette as a tint color. This is effective for views that support tinting, such as `ImageView`, `CompoundButton`, and any views implementing `TintableBackgroundView`.

```kotlin
myCheckBox.rainbow().palette {
  +contextColor(R.color.red_200)
  +contextColor(R.color.blue_200)
}.tint()
```

### Retrieving as a Drawable

If you need the gradient as a `GradientDrawable` object for use elsewhere, you can retrieve it with the `getDrawable` method.

```kotlin
val gradientDrawable = Rainbow(myView).palette {
  +contextColor(R.color.red_200)
  +contextColor(R.color.yellow_200)
}.getDrawable()

// Now you can use the drawable instance as needed.
someOtherView.background = gradientDrawable
```

### Declarative Gradient Views

For declarative UI construction in XML layouts, `Rainbow` provides custom `View` implementations. First, ensure the `app` XML namespace is declared in your root layout element:

```xml
xmlns:app="http://schemas.android.com/apk/res-auto"
```

#### RainbowView

`RainbowView` is a versatile view that renders a multi-color gradient. It sources its colors from a color-array resource.

```xml
<com.skydoves.rainbow.RainbowView
  android:id="@+id/rainbowView"
  android:layout_width="match_parent"
  android:layout_height="100dp"
  app:rainbowView_colors="@array/rainbow_colors"
  app:rainbowView_orientation="left_right"
  app:rainbowView_radius="12dp" />
```

#### BinaryRainbowView

`BinaryRainbowView` is a simplified version for creating two or three-color gradients directly from color attributes.

```xml
<com.skydoves.rainbow.BinaryRainbowView
  android:id="@+id/binaryRainbowView"
  android:layout_width="match_parent"
  android:layout_height="80dp"
  app:binaryRainbowView_startColor="@color/md_green_100"
  app:binaryRainbowView_centerColor="@color/white"
  app:binaryRainbowView_endColor="@color/skyBlue"
  app:binaryRainbowView_orientation="bottom_top"
  app:binaryRainbowView_radius="12dp" />
```

### Shuffling Gradient Colors

Both `RainbowView` and `BinaryRainbowView` support dynamic shuffling of their palette colors. Calling the `shuffleColors()` method will randomly reorder the gradient colors, providing a simple way to create dynamic visual effects.

```kotlin
val rainbowView = findViewById<RainbowView>(R.id.rainbowView)
rainbowView.shuffleColors()

val binaryRainbowView = findViewById<BinaryRainbowView>(R.id.binaryRainbowView)
binaryRainbowView.shuffleColors()
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
