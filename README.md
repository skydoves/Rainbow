# Rainbow

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=16"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
🌈 An easy way to give gradation effect and tinting for Android.
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/63633474-2a597800-c684-11e9-8aa3-db99575c73e0.png" width="694" height="235"/>
</p>

## Download
### Gradle
Add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:rainbow:1.0.0"
}
```

## Usage
We can give gradations effect and tinting to any views easily using `Rainbow` class.

### Palette
Palette lambda expression collects colors for creating gradation.<br>
We can collect colors using `contextColor` and `color` functions.<br>
`contextColor` gets a __resource__ color from your `colors.xml` file, and `color` gets a __ColorInt__ color.<br>
They should be used with `+` operation in the palette lambda expression.

```kotlin
Rainbow(myCardView).palette { // constructs a palette for collecting colors.
  +contextColor(R.color.red_200) // getting a color from the resource
  +contextColor(R.color.yellow_200)
  +contextColor(R.color.green_200)
  +contextColor(R.color.blue_200)
  +color(Color.WHITE) // getting a color
}.withAlpha(225) // sets alpha
 .foreground() // gives gradation effect to myCardView
```

### Background, Foreground
We can give gradations effect composed with palette colors to the background or foreground.<br>
The `forground()` would be used for your `CardView` or something others.

```kotlin
Rainbow(myCardView).palette { // constructs a palette for collecting colors.
  +contextColor(R.color.red_200) // getting a color from the resource
  +contextColor(R.color.yellow_200)
}.background() or .foreground()
```

And we can control the gradation orientation and corner radius. <br>
We can change eight kinds of orientation using `RainbowOrientation`.
```kotlin
background(orientation = RainbowOrientation.RIGHT_LEFT, radius = 8)
background(orientation = RainbowOrientation.TOP_BOTTOM, radius = 8)
foreground(orientation = RainbowOrientation.DIAGONAL_TOP_LEFT, radius = 8)
foreground(orientation = RainbowOrientation.DIAGONAL_BOTTOM_RIGHT, radius = 8)
```

### Tinting
We can change tint colors to some kinds of views which can be tint.

```kotlin
Rainbow(myCheckBox).palette {
  +contextColor(R.color.red_200)
  +contextColor(R.color.yellow_200)
}.tint()
```

### Drawable
You can get a `GradientDrawable` using `getDrawable` method. <br>

```kotlin
val drawable = Rainbow(myCheckBox).palette {
  +contextColor(R.color.red_200)
  +contextColor(R.color.yellow_200)
}.getDrawable()
```

### RainbowView
RainbowView is a gradient view for implementing a gradation effect. <br><br>
Add following XML namespace inside your XML layout file.

```gradle
xmlns:app="http://schemas.android.com/apk/res-auto"
```

#### RainbowView in xml layout
```gradle
<com.skydoves.rainbow.RainbowView
  android:id="@+id/rainbow"
  android:layout_width="match_parent"
  android:layout_height="120dp"
  app:rainbowView_colors="@array/colors" // colors for gradient.
  app:rainbowView_orientation="left_right" // gradient orientation.
  app:rainbowView_radius="12dp" // corner radius.
/>
```

Tha `rainbowView_colors` attributes gets color list from the color-array from your `colors.xml`.

```gradle
<resources>
  <color name="colorPrimary">#C51162</color>
  ...
  <array name="colors">
    <item>@color/md_yellow_100</item>
    <item>@color/md_orange_100</item>
    <item>@color/md_green_100</item>
    <item>@color/md_blue_100</item>
  </array>
</resources>
```

### BinaryRainbowView
BinaryRainbowView is a gradient view for implementing a simple gradation effect. <br>

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