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

import android.graphics.drawable.GradientDrawable

/** RainbowOrientation is the orientation attribute for expressing gradation. */
enum class RainbowOrientation(val value: GradientDrawable.Orientation) {
  TOP_BOTTOM(GradientDrawable.Orientation.TOP_BOTTOM),
  DIAGONAL_TOP_RIGHT(GradientDrawable.Orientation.TR_BL),
  RIGHT_LEFT(GradientDrawable.Orientation.RIGHT_LEFT),
  DIAGONAL_BOTTOM_RIGHT(GradientDrawable.Orientation.BR_TL),
  BOTTOM_TOP(GradientDrawable.Orientation.BOTTOM_TOP),
  DIAGONAL_BOTTOM_LEFT(GradientDrawable.Orientation.BL_TR),
  LEFT_RIGHT(GradientDrawable.Orientation.LEFT_RIGHT),
  DIAGONAL_TOP_LEFT(GradientDrawable.Orientation.TL_BR);

  /** gets [RainbowOrientation] using index value. */
  companion object {

    @JvmStatic
    fun get(value: Int): RainbowOrientation {
      return when (value) {
        0 -> TOP_BOTTOM
        1 -> DIAGONAL_TOP_RIGHT
        2 -> RIGHT_LEFT
        3 -> DIAGONAL_BOTTOM_RIGHT
        4 -> BOTTOM_TOP
        5 -> DIAGONAL_BOTTOM_LEFT
        6 -> LEFT_RIGHT
        else -> DIAGONAL_TOP_LEFT
      }
    }
  }
}
