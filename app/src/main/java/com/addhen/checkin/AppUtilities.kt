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

package com.addhen.checkin

import android.app.Application
import timber.log.Timber
import javax.inject.Inject

class AppUtilities(vararg params: AppUtility) : AppUtility {

  private val appUtilities = params.asList()

  override fun init(application: Application) {
    for (appUtility in appUtilities) {
      appUtility.init(application)
    }
  }
}

class TimberUtility @Inject constructor() : AppUtility {

  override fun init(application: Application) {
    val tree = if (BuildConfig.DEBUG) Timber.DebugTree() else FirebaseCrashTree()
    Timber.plant(tree)
  }
}
