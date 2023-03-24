package id.kputro.pkdex.ui.screen.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.kputro.pkdex.core.datasource.PokemonSource
import id.kputro.pkdex.core.entities.main.PokeResult
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.extension.LOG_TAG
import id.kputro.pkdex.core.extension.onFailure
import id.kputro.pkdex.core.extension.onLoading
import id.kputro.pkdex.core.extension.onSuccess
import id.kputro.pkdex.core.repositories.MainRepository
import id.kputro.pkdex.session.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
  private val session: UserSession,
  private val mainRepository: MainRepository,
  private val pokemonSource: PokemonSource,
) : ViewModel() {

  private lateinit var navController: NavController

  private val _showLoading = mutableStateOf(false)
  val showLoading: MutableState<Boolean> = _showLoading

  fun start(navController: NavController) {
    this.navController = navController
  }

  val pokemonList: Flow<PagingData<Pokemon>> = Pager(
    PagingConfig(pageSize = 10, initialLoadSize = 10)
  ) {
    pokemonSource
  }.flow.cachedIn(viewModelScope)
}