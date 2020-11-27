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

package com.skydoves.rainbowdemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import com.skydoves.rainbow.contextColor
import com.skydoves.rainbow.rainbow
import com.skydoves.rainbowdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    with(binding) {
      cardView0.rainbow().palette {
        +contextColor(R.color.skyBlue)
        +contextColor(R.color.colorPrimary)
      }.background(orientation = RainbowOrientation.TOP_BOTTOM, radius = 8)

      Rainbow(cardView0).palette {
        +contextColor(R.color.skyBlue)
        +contextColor(R.color.colorPrimary)
        +color(Color.WHITE)
        +contextColor(R.color.yellow)
      }.foreground()

      Rainbow(cardView1).palette {
        +contextColor(R.color.colorPrimary)
        +contextColor(R.color.md_orange_100)
        +contextColor(R.color.md_yellow_100)
        +contextColor(R.color.md_green_200)
        +contextColor(R.color.md_blue_200)
        +contextColor(R.color.md_purple_100)
      }.foreground(orientation = RainbowOrientation.LEFT_RIGHT)

      Rainbow(cardView2).palette {
        +contextColor(R.color.colorPrimary)
        +contextColor(R.color.md_orange_100)
        +contextColor(R.color.md_yellow_100)
        +contextColor(R.color.md_green_200)
        +contextColor(R.color.md_blue_200)
        +contextColor(R.color.md_purple_100)
      }.foreground(orientation = RainbowOrientation.TOP_BOTTOM)

      Rainbow(view0).palette {
        +contextColor(R.color.md_red_400)
        +contextColor(R.color.md_yellow_100)
        +contextColor(R.color.md_green_100)
        +contextColor(R.color.md_blue_100)
        +contextColor(R.color.white)
      }.withAlpha(200)
        .withElevation(12f)
        .background(radius = 16)
    }
  }
}
