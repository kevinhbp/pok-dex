package id.kputro.pkdex.core.network.clients.main

import id.kputro.pkdex.core.entities.login.LoginParameter
import id.kputro.pkdex.core.entities.login.LoginResponse
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.entities.main.PokemonList
import id.kputro.pkdex.core.network.constants.AppEnv
import id.kputro.pkdex.core.singletons.DataSingleton

class MainClient(
  private val production: MainEndpoints,
  private val staging: MainEndpoints,
) : MainContract.MainCommon, MainContract.MainAuth {

  private fun getService(): MainEndpoints {
    val env = DataSingleton().currentAppEnv
    return if (env == AppEnv.STAGING) staging else production
  }

  override suspend fun getPokemonList(offset: Int, limit: Int): PokemonList =
    getService().getPokemonList(offset, limit)

  override suspend fun getPokemonDetail(id: Any): Pokemon =
    getService().getPokemonDetail(id)

  override suspend fun doLogin(userId: String, password: String): LoginResponse? =
    getService().doLogin(LoginParameter(userId, password)).data
}