package id.kputro.pkdex.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import id.kputro.pkdex.R
import id.kputro.pkdex.ui.components.AppBarLv1
import id.kputro.pkdex.ui.components.appBarHeight
import id.kputro.pkdex.ui.navigation.DoublePressedToExit
import id.kputro.pkdex.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = koinViewModel()) {
  DoublePressedToExit()

  LaunchedEffect(key1 = true, block = {
    viewModel.start(navController)
  })

  MainContent()
}

@Composable
fun MainContent() {
  Surface {
    Column(
      modifier = Modifier
        .fillMaxSize()
    ) {

    }

    AppBarLv1 { MainAppBarContent() }
  }
}

/// ---
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
  MainContent()
}

/// ---
@Composable
fun MainAppBarContent() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(start = Spacing.x6),
    contentAlignment = Alignment.CenterStart,
  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_logo),
      contentDescription = "logo-white",
      contentScale = ContentScale.Fit,
      modifier = Modifier.height(appBarHeight * 0.32f)
    )
  }
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(end = Spacing.x4),
    contentAlignment = Alignment.CenterEnd,
  ) {
    IconButton(onClick = { }) {
      Icon(imageVector = Icons.Outlined.Person, "Open profile", tint = Color.White)
    }
  }
}
