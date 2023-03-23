package id.kputro.pkdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import id.kputro.pkdex.ui.theme.ColorComponent

@Composable
fun TopShadow() {
  Box(modifier = Modifier.fillMaxWidth()
    .height(60.dp)
    .background(
      brush = Brush.verticalGradient(
        colors = listOf(
          ColorComponent.shadow1,
          ColorComponent.shadow2,
        )
      )
    )) {
  }
}