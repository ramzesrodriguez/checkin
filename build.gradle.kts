// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
  repositories {
    google()
    jcenter()
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}")
    classpath(kotlin("gradle-plugin", version = Versions.KOTLIN))
    classpath("com.google.gms:google-services:3.2.0")
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle.kts files
  }
}

plugins {
  id("io.gitlab.arturbosch.detekt") version Versions.DETEKT
  id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT_GRADLE
}

allprojects {
  repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    // Using glide's snapshot release for a fix for Jetpack because
    // the stable version of Glide doesn't use Jetpack's namespacess
    // even though Jetifier is enabled.
    maven(url = "http://oss.sonatype.org/content/repositories/snapshots")
    maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
  }
}

subprojects {
  apply {
    plugins.apply("org.jlleitschuh.gradle.ktlint")
  }
  ktlint {
    version = Versions.KTLINT
    android = true
    reporters = arrayOf(ReporterType.CHECKSTYLE)
    ignoreFailures = false
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = Versions.SOURCE_COMPATIBILITY
}

task<Delete>("Delete") {
  delete(rootProject.buildDir)
}

