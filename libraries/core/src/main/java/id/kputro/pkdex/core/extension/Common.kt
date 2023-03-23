package id.kputro.pkdex.core.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var debounceJob: Job? = null
fun debouncer(
  waitMs: Long = 200L,
  coroutineScope: CoroutineScope,
  destinationFunction: () -> Unit
) {
  debounceJob?.cancel()
  debounceJob = coroutineScope.launch {
    delay(waitMs)
    destinationFunction()
  }
}