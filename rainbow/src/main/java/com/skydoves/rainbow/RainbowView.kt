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

@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.skydoves.rainbow

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View

/** RainbowView represents gradation effect using colors. */
open class RainbowView : View {

  private var colorList = mutableListOf<Int>()
  var orientation = RainbowOrientation.LEFT_RIGHT
    set(value) {
      field = value
      updateRainbowView()
    }
  var radius = 5
    set(value) {
      field = value
      updateRainbowView()
    }

  constructor(context: Context) : super(context)

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    getAttrs(attributeSet)
  }

  constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
    getAttrs(attributeSet, defStyle)
  }

  private fun getAttrs(attributeSet: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RainbowView)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RainbowView, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(a: TypedArray) {
    this.radius = px2Dp(a.getDimension(R.styleable.RainbowView_rainbowView_radius, radius.toFloat())).toInt()
    this.orientation = RainbowOrientation.get(
      a.getInt(R.styleable.RainbowView_rainbowView_orientation, RainbowOrientation.LEFT_RIGHT.ordinal)
    )
    val colorsId = a.getResourceId(R.styleable.RainbowView_rainbowView_colors, 0)
    this.colorList = a.resources.getIntArray(colorsId).toMutableList()
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    updateRainbowView()
  }

  private fun updateRainbowView() {
    Rainbow(this)
      .addColorList(colorList)
      .background(orientation, radius)
  }

  /** adds color to the gradation color set. */
  fun addColor(color: Int) {
    this.colorList.add(color)
    updateRainbowView()
  }

  /** removes color from the gradation color set. */
  fun removeColor(index: Int) {
    this.colorList.remove(index)
    updateRainbowView()
  }

  /** shuffles gradation colors in the color set. */
  fun shuffleColors() {
    this.colorList.shuffle()
    updateRainbowView()
  }
}
