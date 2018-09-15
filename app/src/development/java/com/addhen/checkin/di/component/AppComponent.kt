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

package com.addhen.checkin.di

import com.addhen.checkin.CheckinApp
import com.addhen.checkin.base.di.module.ViewModelBuilder
import com.addhen.checkin.di.scope.ActivityScope
import com.addhen.checkin.main.view.MainBuilder
import com.addhen.checkin.view.posts.PostsBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Initializes development flavor related dagger components.
 */
@ActivityScope
@Singleton
@Component(
    modules = [
      AndroidSupportInjectionModule::class,
      DevelopmentAppModule::class,
      ViewModelBuilder::class,
      MainBuilder::class,
      PostsBuilder::class])
interface AppComponent : AndroidInjector<CheckinApp> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<CheckinApp>()
}
