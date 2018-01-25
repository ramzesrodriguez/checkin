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


import com.hellofresh.barcodescanner.data.api.BarcodeScannerService
import com.hellofresh.barcodescanner.data.api.MockBarcodeScannerService
import com.hellofresh.barcodescanner.data.repository.auth.AuthDataSource
import com.hellofresh.barcodescanner.data.repository.auth.AuthRepository
import com.hellofresh.barcodescanner.data.repository.scanlog.CarrierDataSource
import com.hellofresh.barcodescanner.data.repository.scanlog.CarrierRepository
import com.hellofresh.barcodescanner.data.repository.scanlog.MockScanLogRepository
import com.hellofresh.barcodescanner.data.repository.scanlog.ScanLogDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Production related dagger modules.
 */
@Module
class ProductionAppModule {

  @Provides
  @Singleton
  fun providePostRepository(postRepository: PostDataRepository): PostRepository = postRepository
}
