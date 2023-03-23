package id.kputro.pkdex.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import id.kputro.pkdex.R
import id.kputro.pkdex.ui.theme.*

@Composable
fun LoadingDialog(showDialog: MutableState<Boolean>, message: String = "Please wait") {
  if (showDialog.value) {
    Dialog(onDismissRequest = { showDialog.value = false }) {
      LoadingDialogContent(message = message)
    }
  }
}

@Composable
fun LoadingDialogContent(
  modifier: Modifier = Modifier,
  message: String = "Please wait",
  darkMode: Boolean = isSystemInDarkTheme(),
) {
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_1))
  Card(
    shape = ComponentShape.dialog,
    modifier = Modifier,
    elevation = Spacing.x1,
    backgroundColor = if (darkMode) ColorComponentDark.surface2 else ColorComponent.surface2
  ) {
    Column(
      modifier = modifier.padding(horizontal = Spacing.x5),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(60.dp),
        contentScale = ContentScale.Fit,
      )
      TextView(
        message,
        style = Typography.labelMedium,
        modifier = Modifier.padding(bottom = Spacing.x2).offset(y = -(Spacing.x2)),
      )
    }
  }
}