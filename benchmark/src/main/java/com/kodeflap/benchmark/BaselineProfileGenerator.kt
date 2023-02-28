package com.kodeflap.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

@ExperimentalBaselineProfilesApi
class BaselineProfileGenerator {

  @get: Rule
  val baselineProfileRule = BaselineProfileRule()

  @Test
  fun startUp() = baselineProfileRule.collectBaselineProfile(
    packageName = "com.kodeflap.pico"
  ) {
    pressHome()
    startActivityAndWait()
    device.waitForIdle()
  }
}
