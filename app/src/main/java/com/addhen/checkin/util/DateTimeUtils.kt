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

package com.addhen.checkin.util

object DateTimeUtils {

  private val SECOND_MILLIS = 1000
  private val MINUTE_MILLIS = 60 * SECOND_MILLIS
  private val HOUR_MILLIS = 60 * MINUTE_MILLIS
  private val DAY_MILLIS = 24 * HOUR_MILLIS

  // Credits: https://goo.gl/7K3xcv
  fun getTimeAgo(time: Long): String? {
    var time = time
    if (time < 1000000000000L) {
      //if timestamp given in seconds, convert to millis
      time *= 1000
    }
    val now = System.currentTimeMillis()
    if (time > now || time <= 0) {
      return null
    }
    return timeDifference(now, time)
  }

  private fun timeDifference(now: Long, time: Long): String? {
    val diff = now - time
    return if (diff < MINUTE_MILLIS) {
      "just now"
    } else if (diff < 2 * MINUTE_MILLIS) {
      "a minute ago"
    } else if (diff < 50 * MINUTE_MILLIS) {
      (diff / MINUTE_MILLIS).toString() + " minutes ago"
    } else if (diff < 90 * MINUTE_MILLIS) {
      "an hour ago"
    } else if (diff < 24 * HOUR_MILLIS) {
      (diff / HOUR_MILLIS).toString() + " hours ago"
    } else if (diff < 48 * HOUR_MILLIS) {
      "yesterday"
    } else {
      (diff / DAY_MILLIS).toString() + " days ago"
    }
  }
}
