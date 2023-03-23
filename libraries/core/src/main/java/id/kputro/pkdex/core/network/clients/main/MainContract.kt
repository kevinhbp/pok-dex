package id.kputro.pkdex.core.network.clients.main

import id.kputro.pkdex.core.entities.login.LoginResponse
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.entities.main.PokemonList

interface MainContract {

  interface MainCommon {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonList
    suspend fun getPokemonDetail(id: Any): Pokemon
  }

  interface MainAuth {
    suspend fun doLogin(userId: String, password: String): LoginResponse?
  }
}