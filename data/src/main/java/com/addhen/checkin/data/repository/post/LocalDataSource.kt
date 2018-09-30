package com.addhen.checkin.data.repository.post

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.addhen.checkin.data.room.CheckinDatabase
import com.addhen.checkin.data.room.entity.PostEntity
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val checkinDatabase: CheckinDatabase) {

  fun getPosts(limit: Int, page: Int): LiveData<List<PostEntity>> {
    return checkinDatabase.postDao().getPost(limit, page)
  }

  @WorkerThread
  suspend fun getPost(id: Long) = checkinDatabase.postDao().getPost(id)
}
