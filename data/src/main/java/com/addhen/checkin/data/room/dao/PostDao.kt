package com.addhen.checkin.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.OnConflictStrategy
import com.addhen.checkin.data.model.Post
import com.addhen.checkin.data.model.Post.Companion.POSTS

@Dao
abstract class PostDao {

  @Transaction
  @Query("SELECT * FROM posts ORDER BY id LIMIT :count OFFSET :offset")
  abstract fun getPost(count: Int, offset: Int): LiveData<List<Post>>

  @Transaction
  @Query("SELECT * FROM posts WHERE id = :id")
  abstract fun getPost(id: Long): Post

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insert(post: Post)

  @Query("DELETE FROM $POSTS")
  abstract fun deleteAll()
}
