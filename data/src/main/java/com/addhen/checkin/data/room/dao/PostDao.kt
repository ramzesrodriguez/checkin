package com.addhen.checkin.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.addhen.checkin.data.room.entity.PostEntity
import com.addhen.checkin.data.room.entity.PostEntity.Companion.POSTS

@Dao
abstract class PostDao {

  @Transaction
  @Query("SELECT * FROM posts ORDER BY id LIMIT :count OFFSET :offset")
  abstract fun getPost(count: Int, offset: Int): LiveData<List<PostEntity>>

  @Transaction
  @Query("SELECT * FROM posts WHERE id = :id")
  abstract fun getPost(id: Long): PostEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insert(post: PostEntity)

  @Query("DELETE FROM $POSTS")
  abstract fun deleteAll()
}
