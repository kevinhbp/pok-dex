package id.kputro.pkdex.core.network.clients.ticu

import id.kputro.pkdex.core.entities.login.LoginParameter
import id.kputro.pkdex.core.entities.login.LoginResponse
import id.kputro.pkdex.core.entities.region.Region
import id.kputro.pkdex.core.network.constants.AppEnv
import id.kputro.pkdex.core.singletons.DataSingleton

class TicuClient(
  private val production: TicuEndpoints,
  private val staging: TicuEndpoints,
) : TicuContract.TicuCommon, TicuContract.TicuAuth {

  private fun getService(): TicuEndpoints {
    val env = DataSingleton().currentAppEnv
    return if (env == AppEnv.STAGING) staging else production
  }

  override suspend fun getRegions(): List<Region> = getService().getRegions().data.orEmpty()

  override suspend fun doLogin(
    userId: String, password: String, regionCode: String
  ): LoginResponse? = getService().doLogin(LoginParameter(userId, password, regionCode)).data
}