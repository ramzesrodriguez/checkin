package com.addhen.checkin.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.addhen.checkin.data.model.Post.Companion.POSTS
import java.util.Date

@Entity(
    tableName = POSTS,
    indices = [
      Index(value = ["location_id"], unique = true),
      Index(value = ["like_id"], unique = true),
      Index(value = ["user_id"], unique = true)
    ],
    foreignKeys = [
      ForeignKey(entity = Location::class,
          parentColumns = arrayOf("id"),
          childColumns = arrayOf("location_id"),
          onUpdate = ForeignKey.CASCADE,
          onDelete = ForeignKey.CASCADE),
      ForeignKey(entity = Like::class,
          parentColumns = arrayOf("id"),
          childColumns = arrayOf("like_id"),
          onUpdate = ForeignKey.CASCADE,
          onDelete = ForeignKey.CASCADE),
      ForeignKey(entity = Like::class,
          parentColumns = arrayOf("id"),
          childColumns = arrayOf("user_id"),
          onUpdate = ForeignKey.CASCADE,
          onDelete = ForeignKey.CASCADE)
    ]
)
data class Post(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long,
  @ColumnInfo(name = "content") val content: String,
  @ColumnInfo(name = "photo") val photo: String,
  @ColumnInfo(name = "location_id") val location: Location,
  @ColumnInfo(name = "created") val created: Date,
  @ColumnInfo(name = "likes") val likes: Long,
  @ColumnInfo(name = "like_id") val like: Like,
  @ColumnInfo(name = "user_id") val user: User
) : Model {

  companion object {
    const val POSTS = "posts"
  }
}
