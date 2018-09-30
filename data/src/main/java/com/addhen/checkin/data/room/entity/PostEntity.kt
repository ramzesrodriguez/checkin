package com.addhen.checkin.data.room.entity

import androidx.room.*
import com.addhen.checkin.data.model.Like
import com.addhen.checkin.data.model.Location
import com.addhen.checkin.data.model.User
import com.addhen.checkin.data.room.entity.PostEntity.Companion.POSTS
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
data class PostEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "location_id") val location: Location,
    @ColumnInfo(name = "created") val created: Date,
    @ColumnInfo(name = "likes") val likes: Long,
    @ColumnInfo(name = "like_id") val like: Like,
    @ColumnInfo(name = "user_id") val user: User
) : CheckinEntity {

  companion object {
    const val POSTS = "posts"
  }
}
