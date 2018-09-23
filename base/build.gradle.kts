import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
  id("com.android.library")
  id("kotlin-android")
  id("androidx.navigation.safeargs")
}

android {
  compileSdkVersion(Versions.COMPILE_SDK)
  dataBinding.isEnabled = true
  defaultConfig {
    minSdkVersion(Versions.MIN_SDK)
  }
  lintOptions {
    disable("GradleCompatible")
  }
}

dependencies {
  api(Dependencies.Kotlin.stdLib)
  api(Dependencies.Kotlin.coroutines)
  // AndroidX
  api(Dependencies.AndroidX.Support.design)
  api(Dependencies.AndroidX.Support.recyclerView)
  api(Dependencies.AndroidX.Support.customtabs)
  api(Dependencies.AndroidX.Support.cardview)
  api(Dependencies.AndroidX.Support.constraintLayout)
  api(Dependencies.AndroidX.Navigation.fragment)
  api(Dependencies.AndroidX.Navigation.ui)
  api(Dependencies.ktx)
  api(Dependencies.Glide.core)
  api(Dependencies.Glide.okhttp3)
  // Architecture components lifecycle
  api(Dependencies.Lifecycle.runtime)
  api(Dependencies.Lifecycle.extentions)
  // Utility
  api(Dependencies.Dagger.core)
  api(Dependencies.Dagger.android)
  api(Dependencies.Dagger.support)
  api(Dependencies.timber)
  kapt(Dependencies.Databinding.compiler)
  kapt(Dependencies.Lifecycle.compiler)
  kapt(Dependencies.Glide.compiler)
}
