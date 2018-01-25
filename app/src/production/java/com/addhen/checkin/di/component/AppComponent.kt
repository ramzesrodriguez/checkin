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

package com.hellofresh.barcodescanner.presentation.di.component

import com.hellofresh.barcodescanner.BarcodeScannerApp
import com.hellofresh.barcodescanner.presentation.di.module.ActivityBuilder
import com.hellofresh.barcodescanner.presentation.di.module.AppModule
import com.hellofresh.barcodescanner.presentation.di.module.DevelopmentAppModule
import com.hellofresh.barcodescanner.presentation.di.module.JobIntentServiceModule
import com.hellofresh.barcodescanner.presentation.di.module.JobServiceBuilder
import com.hellofresh.barcodescanner.presentation.di.scope.ActivityScope
import com.hellofresh.barcodescanner.presentation.di.scope.JobIntentServiceScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Initializes production flavor related dagger components.
 */
@ActivityScope
@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (DevelopmentAppModule::class)])
interface AppComponent : AndroidInjector<BarcodeScannerApp> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<BarcodeScannerApp>()
}
