package com.addhen.checkin.data.repository.post

import com.addhen.checkin.data.model.Post
import javax.inject.Inject


class LocalDataSource @Inject constructor() {

  suspend fun getPosts(limit: Int, page: Int): List<Post> {
    // TODO get data from a data source
    return emptyList()
  }

  suspend fun getPost(id: String): Post {
    // TODO get data from a data source
    TODO()
  }
}
