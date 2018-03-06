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

package com.hellofresh.barcodescanner.presentation.view.base

import android.content.Context
import android.support.annotation.UiThread
import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView


abstract class BaseRecyclerAdapter<T, V : RecyclerView.ViewHolder> constructor(
    val context: Context) : RecyclerView.Adapter<V>() {

  protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
  protected abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
  private val diffCallback = object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
      return this@BaseRecyclerAdapter.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
      return this@BaseRecyclerAdapter.areContentsTheSame(oldItem, newItem)
    }
  }
  private val asyncListDiffer = AsyncListDiffer(this, diffCallback)

  @UiThread
  fun reset(items: List<T>) {
    asyncListDiffer.submitList(items)
  }

  override fun getItemCount(): Int {
    return asyncListDiffer.currentList.size
  }

  fun getItem(position: Int): T {
    return asyncListDiffer.currentList.elementAt(position)
  }

}
