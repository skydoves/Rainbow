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

@file:Suppress("MemberVisibilityCanBePrivate")

package com.skydoves.rainbow

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

/** BinaryRainbowView represents gradation effect using three colors. */
class BinaryRainbowView : View {

  @ColorInt
  var startColor = outRangeColor
    set(value) {
      field = value
      updateBinaryRainbowView()
    }

  @ColorInt
  var centerColor = outRangeColor
    set(value) {
      field = value
      updateBinaryRainbowView()
    }

  @ColorInt
  var endColor = outRangeColor
    set(value) {
      field = value
      updateBinaryRainbowView()
    }

  var orientation = RainbowOrientation.LEFT_RIGHT
    set(value) {
      field = value
      updateBinaryRainbowView()
    }

  @Dp
  var radius = 5
    set(value) {
      field = value
      updateBinaryRainbowView()
    }

  constructor(context: Context) : super(context)

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    getAttrs(attributeSet)
  }

  constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
    context,
    attributeSet, defStyle
  ) {
    getAttrs(attributeSet, defStyle)
  }

  private fun getAttrs(attributeSet: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BinaryRainbowView)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray =
      context.obtainStyledAttributes(attributeSet, R.styleable.BinaryRainbowView, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(a: TypedArray) {
    this.radius =
      px2Dp(a.getDimension(R.styleable.RainbowView_rainbowView_radius, radius.toFloat())).toInt()
    this.orientation = RainbowOrientation.get(
      a.getInt(
        R.styleable.BinaryRainbowView_binaryRainbowView_orientation,
        RainbowOrientation.LEFT_RIGHT.ordinal
      )
    )
    this.startColor =
      a.getColor(R.styleable.BinaryRainbowView_binaryRainbowView_startColor, outRangeColor)
    this.centerColor =
      a.getColor(R.styleable.BinaryRainbowView_binaryRainbowView_centerColor, outRangeColor)
    this.endColor =
      a.getColor(R.styleable.BinaryRainbowView_binaryRainbowView_endColor, outRangeColor)
  }

  private fun updateBinaryRainbowView() {
    val colorList: MutableList<Int> = ArrayList()
    if (startColor != outRangeColor) colorList.add(startColor)
    if (centerColor != outRangeColor) colorList.add(centerColor)
    if (endColor != outRangeColor) colorList.add(endColor)

    Rainbow(this)
      .addColorList(colorList)
      .background(orientation, radius)
  }
}
