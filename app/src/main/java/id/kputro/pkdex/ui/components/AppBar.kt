package id.kputro.pkdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.kputro.pkdex.ui.theme.ColorComponent

val appBarHeight = 56.dp

@Suppress("UNUSED_PARAMETER")
@Composable
fun AppBarLv1(
  darkMode: Boolean = isSystemInDarkTheme(),
  content: @Composable BoxScope.() -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .windowInsetsTopHeight(WindowInsets.statusBars)
      .background(
        color = ColorComponent.appBar1,
      )
  )
  Box(modifier = Modifier
    .statusBarsPadding()
    .fillMaxWidth()) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(appBarHeight)
        .background(
          color = ColorComponent.appBar1,
          shape = RoundedCornerShape(bottomEnd = 40f)
        ),
      content = content
    )
  }
}