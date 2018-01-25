package com.hellofresh.barcodescanner.presentation.di.module


import com.addhen.checkin.data.repository.PostDataRepository
import com.addhen.checkin.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Development related dagger modules.
 */
@Module
class DevelopmentAppModule {

  @Provides
  @Singleton
  fun providePostRepository(postRepository: PostDataRepository): PostRepository = postRepository
}
