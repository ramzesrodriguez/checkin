package com.addhen.checkin.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

  companion object {

    private const val DB_NAME = "checkin_app.db"

    fun createPersistentDatabase(
      context: Context
    ): CheckinDatabase = Room.databaseBuilder(
        context.applicationContext,
        CheckinDatabase::class.java,
        DB_NAME
    ).build()
  }
}
