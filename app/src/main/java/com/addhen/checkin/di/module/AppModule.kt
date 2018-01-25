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

package com.hellofresh.barcodescanner.presentation.di.module

import com.addhen.checkin.AppUtilities
import com.addhen.checkin.CheckinApp
import com.addhen.checkin.TimberUtility
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


/**
 * Dagger module generally available for the entire lifecycle of the app.
 */

@Module(includes = [(ViewModelBuilder::class)])
class AppModule {

  @Singleton
  @Provides
  fun provideAppContext(app: CheckinApp) = app.applicationContext

  @Provides
  fun provideCompositeDisposable() = CompositeDisposable()

  @Provides
  fun provideAppUtilities(timberUtility: TimberUtility): AppUtilities = AppUtilities(timberUtility)
}
