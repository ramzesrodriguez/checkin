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

package com.addhen.checkin.di.module

import com.addhen.checkin.data.repository.PostDataRepository
import com.addhen.checkin.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Production related dagger modules.
 */
@Module(includes = [AppModule::class])
internal object ProductionAppModule {

  @Provides
  @Singleton
  @JvmStatic
  fun providePostRepository(postRepository: PostDataRepository): PostRepository = postRepository
}
