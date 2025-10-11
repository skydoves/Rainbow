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

package com.skydoves.rainbow

import android.util.DisplayMetrics
import android.view.View

internal const val outRangeColor = 65555

/** dp size to px size. */
internal fun View.dp2Px(dp: Int): Float =
  dp * resources.displayMetrics.density

/** px size to dp size. */
internal fun View.px2Dp(px: Float): Float =
  px / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
