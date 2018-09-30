package com.addhen.checkin.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class PostDao {

  @Transaction
  @Query("SELECT * FROM posts ORDER BY id LIMIT :count OFFSET :offset")
  abstract fun getPost(count: Int, offset: Int)
}
