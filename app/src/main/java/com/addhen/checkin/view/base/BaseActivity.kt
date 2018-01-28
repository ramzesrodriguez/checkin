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


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.v7.app.AppCompatDelegate
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/**
 * Base {@link AppCompatActivity} class that all classes that wants to extend {@link
 * AppCompatActivity } must extend. Handles a lot of the repetitive initializations. For example
 * to load the layout and menu, you provide the super constructor with the layout and menu resIds
 * and it will automatically setup the layout and menu for you.
 */
abstract class BaseActivity<out T : BaseViewModel, out B : ViewDataBinding>(
    @LayoutRes
    protected val layout: Int,
    @MenuRes
    private val menu: Int = 0,
    clazz: Class<T>) : DaggerAppCompatActivity() {
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  protected val binding: B by lazy {
    DataBindingUtil.setContentView<B>(this, layout)
  }
  val viewModel by lazy {
    ViewModelProviders.of(this@BaseActivity, viewModelFactory).get(clazz)
  }

  init {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    if (this.menu != 0) {
      menuInflater.inflate(this.menu, menu)
    }
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
      else -> {
        return super.onOptionsItemSelected(item)
      }
    }
    return super.onOptionsItemSelected(item)
  }
}

