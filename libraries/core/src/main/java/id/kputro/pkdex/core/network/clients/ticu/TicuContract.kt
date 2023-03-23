package id.kputro.pkdex.core.network.clients.ticu

import id.kputro.pkdex.core.entities.login.LoginResponse
import id.kputro.pkdex.core.entities.region.Region

interface TicuContract {

  interface TicuCommon {
    suspend fun getRegions(): List<Region>
  }

  interface TicuAuth {
    suspend fun doLogin(userId: String, password: String, regionCode: String): LoginResponse?
  }
}