package com.addhen.checkin.data.room

import androidx.room.TypeConverter
import java.util.Date

object CheckinTypeConverters {

  @JvmStatic
  @TypeConverter
  fun toDate(timestamp: Long): Date {
    return Date(timestamp)
  }

  @JvmStatic
  @TypeConverter
  fun fromDate(date: Date): Long {
    return date.time
  }
}
