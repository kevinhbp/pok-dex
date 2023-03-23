package id.kputro.pkdex.core.repositories

import id.kputro.pkdex.core.entities.base.Result
import id.kputro.pkdex.core.network.clients.ticu.TicuClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class AuthRepository(
  private val client: TicuClient,
  private val mockStorage: MockStorage,
) {

  fun fetchRegionList() = flow {
    try {
      emit(Result.Loading)
      val response = mockStorage.getRegions()
      delay(300)
      emit(Result.Success(response))
    } catch (e: Exception) {
      emit(Result.Failure(e))
    }
  }
}