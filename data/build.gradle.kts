import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Versions.COMPILE_SDK)
  defaultConfig {
    minSdkVersion(Versions.MIN_SDK)
    targetSdkVersion(Versions.TARGET_SDK)
  }
  lintOptions {
    disable("GradleCompatible")
  }
}

dependencies {
  api(project(":base"))
  kapt(Dependencies.Databinding.compiler)
  testImplementation(Dependencies.junit)
}
