import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
  id("com.android.library")
  id("kotlin-android")
  id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT_GRADLE
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
  // Support library
  api(Dependencies.Support.design)
  api(Dependencies.Support.recyclerView)
  api(Dependencies.Support.customtabs)
  api(Dependencies.Support.cardview)
  api(Dependencies.Support.constraintLayout)
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

ktlint {
  version = Versions.KTLINT
  android = true
  ignoreFailures = false
}
