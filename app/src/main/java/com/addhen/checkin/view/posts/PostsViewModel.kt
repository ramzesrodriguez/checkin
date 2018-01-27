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

package com.addhen.checkin.view.posts

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.addhen.checkin.data.model.Post
import com.addhen.checkin.data.repository.PostDataRepository
import com.addhen.checkin.view.base.Resource
import com.hellofresh.barcodescanner.presentation.view.base.BaseViewModel
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val postDataRepository: PostDataRepository) : BaseViewModel(), LifecycleObserver {

  val posts = MutableLiveData<Resource<List<PostItemViewModel>>>()

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  override fun onCreate() {
    super.onCreate()
    loadPosts()
  }

  fun onSwipeRefresh() {
    loadPosts()
  }

  private fun loadPosts() {
    postDataRepository.getPosts()
        .doOnSubscribe { posts.value = Resource.loading() }
        .appSubscribeBy(this::onPostLoaded)
  }

  private fun onPostLoaded(posts: List<Post>) {
    val postItemViewModels = posts.map { post ->
      PostItemViewModel(post)
    }
    this.posts.value = Resource.success(postItemViewModels)
  }
}
