package com.kodeflap.pico

object Configuration {
  const val compileSdk = 33
  const val targetSdk = 33
  const val minSdk = 26
  const val majorVersion = 0
  const val minorVersion = 1
  const val patchVersion = 0
  const val versionName = "$majorVersion.$minorVersion.$patchVersion"
  const val versionCode = 1
  const val snapshotVersionName = "$majorVersion.$minorVersion.${patchVersion + 1}-SNAPSHOT"
  const val artifactGroup = "com.kodeflap.pico"
}
