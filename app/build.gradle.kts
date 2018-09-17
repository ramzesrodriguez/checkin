plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
  id("com.google.gms.google-services") apply false
}

val env = "env"
android {
  compileSdkVersion(Versions.COMPILE_SDK)
  dataBinding.isEnabled = true
  defaultConfig {
    applicationId = Versions.APPLICATION_ID
    minSdkVersion(Versions.MIN_SDK)
    targetSdkVersion(Versions.TARGET_SDK)
    setProperty("archivesBaseName", "${project.name}-${versionName}")
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }

  dexOptions {
    preDexLibraries = "true" != System.getenv("CI")
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      isShrinkResources = false
      proguardFile(getDefaultProguardFile("proguard-android.txt"))
      proguardFile(file("proguard-rules.pro"))
    }
  }

  lintOptions {
    lintConfig = file("lint.xml")
    disable("AppCompatResource")
    textReport = true
    textOutput("stdout")
  }

  compileOptions {
    setSourceCompatibility(Versions.SOURCE_COMPATIBILITY)
    setTargetCompatibility(Versions.SOURCE_COMPATIBILITY)
  }

  flavorDimensions(env)

  productFlavors {
    create("development") {
      dimension = env
      applicationId = "${Versions.APPLICATION_ID}.dev"
      testApplicationId = "${Versions.APPLICATION_ID}dev.test"
      // Specify devel base URL. Always end it with the trailing slash because the HTTP client
      // expects it.
      val baseUrl = "http://scan-mock.com/"
      buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
    }

    create("production") {
      dimension = env
    }
  }
}

dependencies {
  implementation(project(":data"))
  implementation(project(":main"))
  // Firebase
  implementation(Dependencies.Firebase.uiAuth)
  implementation(Dependencies.Firebase.crash)
  // Google maps
  implementation(Dependencies.Map.google)
  implementation(Dependencies.Map.utils)
  // Annotation processors
  kapt(Dependencies.Databinding.compiler)
  kapt(Dependencies.Dagger.compiler)
  kapt(Dependencies.Dagger.processor)
  kapt(Dependencies.Lifecycle.compiler)
  kapt(Dependencies.Glide.compiler)
  debugImplementation(Dependencies.Leakcanary.op)
  releaseImplementation(Dependencies.Leakcanary.noOp)
  // Tests
  testImplementation(Dependencies.junit)
}

kapt {
  useBuildCache = true
}
// MUST BE AT THE BOTTOM
apply(mapOf("plugin" to "com.google.gms.google-services"))
