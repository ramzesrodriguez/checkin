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

package com.addhen.checkin.view.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.support.annotation.MenuRes
import android.view.Menu
import android.view.MenuInflater
import com.hellofresh.barcodescanner.presentation.view.base.BaseViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<out T : BaseViewModel, B : ViewDataBinding>(
    @MenuRes
    private val menu: Int = 0,
    clazz: Class<T>) : DaggerFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  protected lateinit var binding: B
  val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(clazz)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    if (this.menu != 0) {
      inflater.inflate(this.menu, menu)
    }
    super.onCreateOptionsMenu(menu, inflater)
  }
}