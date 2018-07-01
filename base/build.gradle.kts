import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  compileSdkVersion(Versions.COMPILE_SDK)
  defaultConfig {
    minSdkVersion(Versions.MIN_SDK)
  }
  lintOptions {
    disable("GradleCompatible")
  }
}

dependencies {
  api(Dependencies.Kotlin.stdLib)
  // Architecture components lifecycle
  api(Dependencies.Lifecycle.runtime)
  api(Dependencies.Lifecycle.extentions)
  // Utility
  api(Dependencies.RxJava2.core)
  api(Dependencies.RxJava2.kotlin)
  implementation(Dependencies.RxJava2.android)
  api(Dependencies.Dagger.core)
  api(Dependencies.Dagger.android)
  api(Dependencies.Dagger.support)
  api(Dependencies.timber)
}
