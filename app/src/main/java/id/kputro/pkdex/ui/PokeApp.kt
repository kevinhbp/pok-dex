package id.kputro.pkdex.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.kputro.pkdex.ui.navigation.Screens
import id.kputro.pkdex.ui.screen.main.MainScreen
import id.kputro.pkdex.ui.theme.PokedexTheme

@Composable
fun PokeApp() {
  PokedexTheme(dynamicColor = false) {
    val navController = rememberNavController()
    SetupAppNavigation(navController = navController)
  }
}

@Composable
fun SetupAppNavigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = Screens.MainScreen.name) {
    composable(Screens.MainScreen.name) {
      MainScreen(navController = navController)
    }
  }
}