/*
 * Copyright (c) 2015 - 2018 Henry Addo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.addhen.checkin.base.databinding

import android.text.TextUtils
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextSwitcher
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.addhen.checkin.base.R

@BindingAdapter(value = ["buttonDrawableRes"])
fun setButtonDrawableRes(button: ImageButton, @DrawableRes drawableResId: Int) {
  button.setImageDrawable(ContextCompat.getDrawable(button.context, drawableResId))
}

@BindingAdapter(value = ["photoImageUrl"])
fun setPhotoImageUrl(imageView: ImageView, imageUrl: String?) {
  setImageUrl(imageView, imageUrl, R.color.grey200)
}

@BindingAdapter(value = ["photoImageUrl", "photoImageSize"])
fun setPhotoImageUrlWithSize(imageView: ImageView, imageUrl: String?, sizeInDimen: Float) {
  if (TextUtils.isEmpty(imageUrl)) {
    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.color.grey200))
    return
  }
  val size = Math.round(sizeInDimen)
  /*GlideApp.with(imageView.context)
      .load(imageUrl)
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .override(size, size)
      .centerCrop()
      .placeholder(R.color.grey200)
      .error(R.color.grey200)
      .transform(CircleCrop())
      .into(imageView)*/
}

@BindingAdapter(value = ["currentText"])
fun setCurrentText(view: TextSwitcher, text: String) {
  view.setCurrentText(text)
}

@BindingAdapter(value = ["coverFadeBackground"])
fun setCoverFadeBackground(view: View, @ColorRes colorResId: Int) {
  view.setBackgroundResource(colorResId)
}

private fun setImageUrlWithSize(imageView: ImageView, imageUrl: String?,
                                sizeInDimen: Float, placeholderResId: Int) {
  if (imageUrl.isNullOrEmpty()) {
    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, placeholderResId))
    return
  }
  val size = Math.round(sizeInDimen)
  imageView.background = ContextCompat.getDrawable(
      imageView.context, R.drawable.shap_circle_border_grey200
  )
  /*GlideApp.with(imageView.context)
      .load(imageUrl)
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .override(size, size)
      .centerCrop()
      .placeholder(placeholderResId)
      .error(placeholderResId)
      .transform(CircleCrop())
      .into(imageView)*/
}

private fun setImageUrl(imageView: ImageView, imageUrl: String?,
                        @DrawableRes placeholderResId: Int) {
  if (imageUrl.isNullOrEmpty()) {
    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, placeholderResId))
    return
  }
  /*GlideApp.with(imageView.context)
      .load(imageUrl)
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .placeholder(placeholderResId)
      .error(placeholderResId)
      .into(imageView)*/
}
