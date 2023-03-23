package id.kputro.pkdex.ui.navigation

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import id.kputro.pkdex.ui.components.showToast

@Composable
fun DoublePressedToExit(
  backPressedDispatcher: OnBackPressedDispatcher? =
    LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
) {
  val activity = (LocalContext.current as? Activity)
  val doubleBackToExitPressedOnce = remember { mutableStateOf(false) }

  val backCallback = remember {
    object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        if (!doubleBackToExitPressedOnce.value) {
          doubleBackToExitPressedOnce.value = true
          activity?.showToast("Press again to exit")
          Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce.value = false
          }, 2000)
        } else if (doubleBackToExitPressedOnce.value) {
          activity?.finish()
        }
      }
    }
  }

  DisposableEffect(key1 = backPressedDispatcher) {
    backPressedDispatcher?.addCallback(backCallback)

    onDispose {
      backCallback.remove()
    }
  }
}