package id.kputro.pkdex.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import id.kputro.pkdex.session.UserSession

class MainViewModel(
  private val session: UserSession,
) : ViewModel() {

  private lateinit var navController: NavController

  fun start(navController: NavController) {
    this.navController = navController
  }
}