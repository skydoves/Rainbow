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

import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/** creates an instance of [ContextColor] using a color value. */
fun Rainbow.color(@ColorInt color: Int): ContextColor = ContextColor(color)

/** creates a list of [ContextColor] using a color list. */
fun Rainbow.colorList(colors: List<Int>): List<ContextColor> {
  return getColorListFromIntArray(colors)
}

/** creates a list of [ContextColor] using a color list. */
fun List<Int>.toContextColorList(): List<ContextColor> {
  return getColorListFromIntArray(this)
}

/** creates a list of [ContextColor] using a color array. */
fun Rainbow.colorArray(colors: IntArray): List<Int> {
  return colors.toList()
}

/** creates a list of [ContextColor] using a color array resource. */
fun Rainbow.colorArray(@ArrayRes array: Int): List<Int> {
  val colors = view.resources.getIntArray(array)
  return colors.toList()
}

/** creates a list of [ContextColor] using a color array. */
fun IntArray.toContextColorList(): List<ContextColor> {
  return getColorListFromIntArray(this.toList())
}

/** creates an instance of [ContextColor] using a resource color value. */
fun Rainbow.contextColor(@ColorRes color: Int): ContextColor = ContextColor(
  ContextCompat.getColor(view.context, color)
)

internal fun getColorListFromIntArray(colors: List<Int>): List<ContextColor> {
  val contextColors = mutableListOf<ContextColor>()
  for (color in colors) {
    contextColors.add(ContextColor(color))
  }
  return contextColors
}
