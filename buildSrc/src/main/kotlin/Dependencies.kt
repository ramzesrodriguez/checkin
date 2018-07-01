object Dependencies {

  val ktx = "androidx.core:core-ktx:${Versions.KTX}"
  val timber = "com.jakewharton.timber:timber:${Versions.TIMBER}"
  val junit = "junit:junit:${Versions.JUNIT}"

  object Kotlin {
    val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
  }

  object Support {
    val design = "com.google.android.material:material:${Versions.SUPPORT_LIBRARY}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.SUPPORT_LIBRARY}"
    val customtabs = "androidx.browser:browser:${Versions.SUPPORT_LIBRARY}"
    val cardview = "androidx.cardview:cardview:${Versions.SUPPORT_LIBRARY}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
  }

  object Lifecycle {
    val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.ARCH_COMPONENTS}"
    val extentions = "androidx.lifecycle:lifecycle-extensions:${Versions.ARCH_COMPONENTS}"
    val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.ARCH_COMPONENTS}"
  }

  object RxJava2 {
    val core = "io.reactivex.rxjava2:rxjava:2.1.8"
    val android = "io.reactivex.rxjava2:rxandroid:2.0.1"
    val kotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
  }

  object Glide {
    val core = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    val okhttp3 = "com.github.bumptech.glide:okhttp3-integration:${Versions.GLIDE}"
    val compiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
  }

  object Databinding {
    val compiler = "androidx.databinding:databinding-compiler:${Versions.GRADLE_PLUGIN}"
  }

  object Leakcanary {
    val op = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"
    val noOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.LEAK_CANARY}"
  }

  object Firebase {
    val uiAuth = "com.firebaseui:firebase-ui-auth:${Versions.FIREBASE}"
    val crash = "com.google.firebase:firebase-crash:11.8.0"
  }

  object Map {
    val google = "com.google.android.gms:play-services-maps:${Versions.PLAY_SERVICES}"
    val utils = "com.google.maps.android:android-maps-utils:0.5"
  }

  object Dagger {
    val core = "com.google.dagger:dagger:${Versions.DAGGER}"
    val android = "com.google.dagger:dagger-android:${Versions.DAGGER}"
    val support = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
    val compiler = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    val processor = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
  }
}
