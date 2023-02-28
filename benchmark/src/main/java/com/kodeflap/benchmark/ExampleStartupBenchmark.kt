package com.kodeflap.benchmark

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance in Hot state.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class HotStartupBenchmark: AbstractStartUpBenchmark(StartupMode.HOT)

/**
* Run this benchmark from Studio to see startup measurements, and captured system traces
* for investigating your app's performance in warm state.
*/
@RunWith(AndroidJUnit4ClassRunner::class)
class WarmStartupBenchmark: AbstractStartUpBenchmark(StartupMode.WARM)

/**
 * * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance in cold state.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ColdStartupBenchmark: AbstractStartUpBenchmark(StartupMode.COLD)

abstract class AbstractStartUpBenchmark(private val startupMode: StartupMode) {
  @get:Rule
  val benchmarkRule = MacrobenchmarkRule()

  @Test
  fun startupBaselineProfile() = startup(CompilationMode.Partial(baselineProfileMode = BaselineProfileMode.Require))

  @Test
  fun startupFullCompilation() =startup(CompilationMode.Full())

  @Test
  fun startupBaselineProfileDisable() = startup(CompilationMode.Partial(baselineProfileMode = BaselineProfileMode.Disable, warmupIterations = 1))

  @Test
  fun startupNoCompilation() = startup(CompilationMode.None())

  private fun startup(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
    packageName = "com.kodeflap.pico",
    metrics = listOf(StartupTimingMetric()),
    compilationMode = compilationMode,
    iterations = 5,
    startupMode = startupMode,
    setupBlock = {
      pressHome()
    }
  ) {
    startActivityAndWait()
  }
}
