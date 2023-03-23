package id.kputro.pkdex.core.repositories

import id.kputro.pkdex.core.entities.base.Result
import id.kputro.pkdex.core.entities.main.PokeResult
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.network.clients.main.MainClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainRepository(
  private val client: MainClient,
  private val mockStorage: MockStorage,
) {
  companion object {
    private const val limit = 5
  }

  private fun getOffset(page: Int): Int = limit * (page - 1)

  fun fetchPokemonList(page: Int) = flow {
    try {
      emit(Result.Loading)
      val response = client.getPokemonList(getOffset(page), limit)
      emit(Result.Success(response.results))
    } catch (e: Exception) {
      emit(Result.Failure(e))
    }
  }

  fun fetchPokemonDetail(pokeResult: PokeResult) = flow {
    try {
      emit(Result.Loading)
      val response = client.getPokemonDetail(pokeResult.name)
      emit(Result.Success(response))
    } catch (e: Exception) {
      emit(Result.Failure(e))
    }
  }
}