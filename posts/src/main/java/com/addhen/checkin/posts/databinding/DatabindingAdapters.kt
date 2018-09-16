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

package com.addhen.checkin.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.addhen.checkin.base.util.DateTimeUtils
import com.addhen.checkin.data.model.User
import com.addhen.checkin.posts.R
import java.util.Date

@BindingAdapter(value = ["userImageUrl", "userImageSize"])
fun setUserImageUrlWithSize(imageView: ImageView, imageUrl: String?, sizeInDimen: Float) {
  setImageUrlWithSize(imageView, imageUrl, sizeInDimen, R.drawable.ic_user_placeholder)
}

@BindingAdapter(value = ["userName"])
fun setUsername(textView: TextView, user: User) {
  textView.text = if (user.fullName != null) user.fullName else "@" + user.username
}

@BindingAdapter(value = ["timeAgo"])
fun setTimeAgo(textView: TextView, date: Date?) {
  if (date != null) {
    textView.text = DateTimeUtils.getTimeAgo(date.time)
  }
}

private fun setImageUrlWithSize(
    imageView: ImageView,
    imageUrl: String?,
    sizeInDimen: Float,
    placeholderResId: Int
) {
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
