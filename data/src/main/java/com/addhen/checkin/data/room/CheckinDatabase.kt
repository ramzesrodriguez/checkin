package com.addhen.checkin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.addhen.checkin.data.model.Post
import com.addhen.checkin.data.room.dao.PostDao

@Database(
    entities = [Post::class],
    version = 1
)
@TypeConverters(CheckinTypeConverters::class)
abstract class CheckinDatabase : RoomDatabase() {

  abstract fun postDao(): PostDao
}
