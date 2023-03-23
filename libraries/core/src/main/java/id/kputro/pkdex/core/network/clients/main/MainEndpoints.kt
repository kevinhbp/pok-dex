package id.kputro.pkdex.core.network.clients.main

import id.kputro.pkdex.core.entities.login.DoLoginResponse
import id.kputro.pkdex.core.entities.login.LoginParameter
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.entities.main.PokemonList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainEndpoints {

  @GET("pokemon")
  suspend fun getPokemonList(
    @Query("offset") offset: Int, @Query("limit") limit: Int
  ): PokemonList

  @GET("pokemon/{id}")
  suspend fun getPokemonDetail(
    @Path("id") id: Any
  ): Pokemon

  @Headers("Content-Type: application/json")
  @POST("auth/login")
  suspend fun doLogin(@Body loginParameter: LoginParameter): DoLoginResponse
}