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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.addhen.checkin.base.Resource
import com.addhen.checkin.base.extension.snackbar
import com.addhen.checkin.base.view.BaseFragment
import com.addhen.checkin.posts.databinding.PostsFragmentBinding

class PostsFragment : BaseFragment<PostsViewModel, PostsFragmentBinding>(
    clazz = PostsViewModel::class.java
) {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = PostsFragmentBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  private fun initView() {
    val postsAdapter = PostsAdapter(requireContext())
    binding.postsRecyclerView.adapter = postsAdapter
    val linearLayoutManager = object : LinearLayoutManager(context) {
      override fun getExtraLayoutSpace(state: RecyclerView.State) = 300
    }
    binding.postsRecyclerView.layoutManager = linearLayoutManager
    viewModel.posts.observe(this, Observer {
      when (it?.status) {
        Resource.Status.SUCCESS -> {
          binding.swipeRefreshLayout.isRefreshing = false
          val list = viewModel.posts.value?.data ?: emptyList()
          val visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
          binding.emptyViewHeader.visibility = visibility
          postsAdapter.reset(list)
        }
        Resource.Status.LOADING -> {
          binding.swipeRefreshLayout.isRefreshing = true
          binding.emptyViewHeader.visibility = View.GONE
        }
        else -> {
          binding.loadingProgressBar.visibility = View.GONE
          binding.emptyViewHeader.visibility = View.GONE
          binding.postRootFramelayout.snackbar(viewModel.posts.value?.message!!)
        }
      }
    })
    lifecycle.addObserver(viewModel)
  }

  companion object {
    const val TAG: String = "PostsFragment"
    fun newInstance(): PostsFragment = PostsFragment()
  }
}
