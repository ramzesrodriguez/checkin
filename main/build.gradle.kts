plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Versions.COMPILE_SDK)
  dataBinding.isEnabled = true
  defaultConfig {
    minSdkVersion(Versions.MIN_SDK)
    targetSdkVersion(Versions.TARGET_SDK)

  }
}

dependencies {
  api(project(":posts"))
  // Tests
  testImplementation(Dependencies.junit)
  kapt(Dependencies.Databinding.compiler)
  kapt(Dependencies.Dagger.compiler)
  kapt(Dependencies.Dagger.processor)
}

kapt {
  useBuildCache = true
}
