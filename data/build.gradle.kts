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
    javaCompileOptions {
      annotationProcessorOptions {
        arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
      }
    }
    sourceSets {
      getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }
  }
  lintOptions {
    disable("GradleCompatible")
  }
}

dependencies {
  api(project(":base"))
  implementation(Dependencies.AndroidX.Room.runtime)
  kapt(Dependencies.AndroidX.Room.compiler)
  kapt(Dependencies.Databinding.compiler)
  testImplementation(Dependencies.junit)
}
