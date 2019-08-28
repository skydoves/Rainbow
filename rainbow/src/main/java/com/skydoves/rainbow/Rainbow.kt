/*
 * Designed and developed by 2019 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.skydoves.rainbow

import android.annotation.TargetApi
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.view.TintableBackgroundView
import androidx.core.view.ViewCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.TextViewCompat

@DslMarker
annotation class RainbowDsl

/** creates an instance of [Rainbow] by a view. */
fun View.rainbow(): Rainbow = Rainbow(this)

/** An easy way to apply gradations and tinting for Android. */
class Rainbow(val view: View) {

  private val rainbowColorList = mutableListOf<ContextColor>()
  private var alpha = 255

  /** constructs a palette for collecting colors. */
  @RainbowDsl
  inline fun palette(block: Rainbow.() -> Unit): Rainbow {
    val rainbow = Rainbow(view)
    rainbow.block()
    return rainbow
  }

  /** adds a color int to the rainbow list. */
  fun addColor(@ColorInt color: Int): Rainbow {
    this.rainbowColorList.add(color(color))
    return this
  }

  /** adds a resource color to the rainbow list.  */
  fun addContextColor(color: Int): Rainbow {
    this.rainbowColorList.add(contextColor(color))
    return this
  }

  /** adds a color list to the rainbow list. */
  fun addColorList(colors: List<Int>): Rainbow {
    this.rainbowColorList.addAll(colorList(colors))
    return this
  }

  /** adds a color array to the rainbow list. */
  fun addColorArray(colors: IntArray): Rainbow {
    this.rainbowColorList.addAll(colorArray(colors))
    return this
  }

  /** sets an alpha value for presenting gradation. */
  fun withAlpha(alpha: Int): Rainbow {
    this.alpha = alpha
    return this
  }

  /**
   * applies a tint to the view which can be tinted.
   *
   * [CompoundButton] - CheckBox, ToggleButton, Switch, RadioButton
   * [TextView]
   * [ImageView]
   * [TintableBackgroundView] - AppCompatAutoCompleteTextView, AppCompatButton, AppCompatEditText,
   *   AppCompatImageButton, AppCompatImageView, AppCompatMultiAutoCompleteTextView, AppCompatSpinner,
   *   AppCompatTextView, EmojiAppCompatButton, EmojiAppCompatEditText, EmojiAppCompatTextView
   */
  fun tint() {
    if (emptyColors()) return
    when (view) {
      is CompoundButton -> CompoundButtonCompat.setButtonTintList(view, getColorStateList())
      is TextView -> TextViewCompat.setCompoundDrawableTintList(view, getColorStateList())
      is ImageView -> ImageViewCompat.setImageTintList(view, getColorStateList())
      else -> ViewCompat.setBackgroundTintList(view, getColorStateList())
    }
  }

  /** applies gradation effect composed with palette colors to the background. For Java. */
  fun background() {
    if (emptyColors()) return
    view.background = getGradientDrawable(RainbowOrientation.LEFT_RIGHT, 0)
  }

  /** applies gradation effect composed with palette colors to the background. */
  fun background(orientation: RainbowOrientation = RainbowOrientation.LEFT_RIGHT, radius: Int = 0) {
    if (emptyColors()) return
    view.background = getGradientDrawable(orientation, radius)
  }

  /** applies gradation effect composed with palette colors to the foreground. For Java. */
  @TargetApi(23)
  fun foreground() {
    if (emptyColors()) return
    view.foreground = getGradientDrawable(RainbowOrientation.LEFT_RIGHT, 0)
  }

  /** applies gradation effect composed with palette colors to the foreground. */
  @TargetApi(23)
  fun foreground(orientation: RainbowOrientation = RainbowOrientation.LEFT_RIGHT, radius: Int = 0) {
    if (emptyColors()) return
    view.foreground = getGradientDrawable(orientation, radius)
  }

  /** gets the gradation drawable which composed with palette colors. For Java. */
  fun getDrawable(): GradientDrawable {
    return getGradientDrawable(RainbowOrientation.LEFT_RIGHT, 0)
  }

  /** gets the gradation drawable which composed with palette colors. */
  fun getDrawable(
    orientation: RainbowOrientation = RainbowOrientation.LEFT_RIGHT,
    radius: Int = 0
  ): GradientDrawable {
    return getGradientDrawable(orientation, radius)
  }

  private fun getGradientDrawable(orientation: RainbowOrientation, radius: Int): GradientDrawable {
    val gradient = GradientDrawable(orientation.value, rainbowColorList.toIntArray())
    gradient.cornerRadius = view.dp2Px(radius)
    gradient.alpha = alpha
    return gradient
  }

  private fun emptyColors(): Boolean = rainbowColorList.isEmpty()

  private fun getColorStateList(): ColorStateList {
    val states = mutableListOf<Int>()
    for (color in rainbowColorList.toIntArray()) {
      states.add(android.R.attr.state_enabled)
    }
    return ColorStateList(arrayOf(states.toIntArray()), rainbowColorList.toIntArray()).withAlpha(alpha)
  }

  operator fun ContextColor.unaryPlus() = rainbowColorList.add(this)

  operator fun List<Int>.unaryPlus() = rainbowColorList.addAll(this.toContextColorList())

  operator fun IntArray.unaryPlus() = rainbowColorList.addAll(this.toContextColorList())
}
