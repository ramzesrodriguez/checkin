/*
 * Copyright (c) 2015 - 2018 Henry Addo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.addhen.checkin.posts.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.addhen.checkin.base.CoroutineDispatchers
import com.addhen.checkin.base.Resource
import com.addhen.checkin.base.view.BaseViewModel
import com.addhen.checkin.data.model.Post
import com.addhen.checkin.data.repository.PostDataRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel @Inject constructor(
  private val dispatchers: CoroutineDispatchers,
  private val postDataRepository: PostDataRepository
) : BaseViewModel(dispatchers), LifecycleObserver {

  val posts = MutableLiveData<Resource<List<PostItemViewModel>>>()

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onSwipeRefresh() {
    loadPosts()
  }

  private fun loadPosts() {
    scope.launch {
      posts.value = Resource.loading()
      var posts = emptyList<Post>()
      withContext(dispatchers.computation) {
        try {
          posts = postDataRepository.getPosts()
        } catch (e: Exception) {
          onError(e)
        }
      }
      onPostLoaded(posts)
    }
  }

  private fun onPostLoaded(posts: List<Post>) {
    val postItemViewModels = posts.map { PostItemViewModel(dispatchers, it) }
    this.posts.value = Resource.success(postItemViewModels)
  }

  private fun onError(throwable: Throwable) {
    Timber.e(throwable)
    this.posts.value = Resource.error(throwable.localizedMessage)
  }
}
