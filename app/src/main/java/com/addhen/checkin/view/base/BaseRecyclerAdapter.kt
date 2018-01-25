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
import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.Callback
import android.support.v7.util.DiffUtil.DiffResult
import android.support.v7.widget.RecyclerView
import com.addhen.checkin.util.RxScheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


abstract class BaseRecyclerAdapter<T, V : RecyclerView.ViewHolder> constructor(
    val context: Context, val rxScheduler: RxScheduler) : RecyclerView.Adapter<V>() {

  private var dataVersion = 0
  protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
  protected abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
  protected var list: MutableList<T> = mutableListOf()
  var compositeDisposable = CompositeDisposable()

  @UiThread
  fun reset(items: Collection<T>) {
    dataVersion++
    when {
      list.isEmpty() -> {
        if (items.isEmpty()) {
          return
        }
        list = items.toMutableList()
        notifyDataSetChanged()
      }
      items.isEmpty() -> {
        val oldSize = list.size
        list = mutableListOf()
        notifyItemRangeRemoved(0, oldSize)
      }
      else -> {
        val startVersion = dataVersion
        val oldItems = list
        calculateDiff(oldItems, items, startVersion)
      }
    }
  }

  override fun getItemCount(): Int = list.size

  fun getItem(position: Int): T = list.elementAt(position)

  fun addItem(item: T, position: Int) {
    var pos = position
    list.add(pos, item)
    notifyItemInserted(pos)
  }

  fun removeItem(item: T) {
    val position = list.indexOf(item)
    list.removeAt(position)
    notifyItemRemoved(position)
  }

  fun initCompositeDisposable() {
    if (compositeDisposable.isDisposed) {
      compositeDisposable = CompositeDisposable()
    }
  }

  private fun calculateDiff(oldItems: Collection<T>, items: Collection<T>, startVersion: Int) {
    Single.fromCallable {
      return@fromCallable DiffUtil.calculateDiff(object : Callback() {
        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = items.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
          val oldItem = oldItems.elementAt(oldItemPosition)
          val newItem = items.elementAt(newItemPosition)
          return this@BaseRecyclerAdapter.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
          val oldItem = oldItems.elementAt(oldItemPosition)
          val newItem = items.elementAt(newItemPosition)
          return this@BaseRecyclerAdapter.areContentsTheSame(oldItem, newItem)
        }
      })
    }.subscribeOn(Schedulers.computation())
        .observeOn(rxScheduler.main)
        .subscribe(
            { onSuccess(startVersion, items, it) },
            { onError(it) }
        ).let(compositeDisposable::add)
  }

  private fun onError(it: Throwable) {
    Timber.e(it)
  }

  private fun onSuccess(startVersion: Int, items: Collection<T>, it: DiffResult) {
    if (startVersion != dataVersion) {
      return
    }
    list = items.toMutableList()
    it.dispatchUpdatesTo(this@BaseRecyclerAdapter)
    //TODO: handle changes in adapter well otherwise the scroll is a bit weird.
    // The newly added item to the list scroll above the screen
    // Calling this for a quick fix
    notifyDataSetChanged()
  }
}
