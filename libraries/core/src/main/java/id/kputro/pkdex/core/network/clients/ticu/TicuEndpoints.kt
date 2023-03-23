package id.kputro.pkdex.core.network.clients.ticu

import id.kputro.pkdex.core.entities.login.DoLoginResponse
import id.kputro.pkdex.core.entities.login.LoginParameter
import id.kputro.pkdex.core.entities.region.GetRegionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TicuEndpoints {

  @GET("master/regions")
  suspend fun getRegions(): GetRegionResponse

  @Headers("Content-Type: application/json")
  @POST("auth/login")
  suspend fun doLogin(@Body loginParameter: LoginParameter): DoLoginResponse
}