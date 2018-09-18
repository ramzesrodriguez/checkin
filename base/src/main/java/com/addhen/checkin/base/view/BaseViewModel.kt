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

package com.addhen.checkin.base.view

import androidx.lifecycle.ViewModel
import com.addhen.checkin.base.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * BaseViewModel base class that is bound to Activity lifecycle and disposes subscriptions on pause
 * call.
 */
abstract class BaseViewModel(dispatchers: CoroutineDispatchers) : ViewModel() {

  private val job = Job()

  protected val scope = CoroutineScope(job + dispatchers.main)

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}
