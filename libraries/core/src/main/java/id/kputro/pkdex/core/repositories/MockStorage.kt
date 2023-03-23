@file:Suppress("SameParameterValue")

package id.kputro.pkdex.core.repositories

import android.content.Context
import com.google.gson.Gson
import id.kputro.pkdex.core.entities.main.PokeResult
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.entities.main.PokemonList
import id.kputro.pkdex.core.network.clients.main.MainContract
import java.nio.charset.Charset

fun Context.getJsonFromAssets(file: MockResponseFile): String {
  var result = ""
  val filename = file.filename
  if (filename.isEmpty()) return result
  try {
    val inputStream = this.assets.open(filename)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    result = String(buffer, Charset.forName("UTF-8"))
  } catch (e: Exception) {
    e.printStackTrace()
  }
  return result
}

enum class MockResponseFile(val filename: String) {
  POKEMON_LIST("get_pokemon_list.json"),
  POKEMON_DETAIL("get_pokemon_detail.json")
}

class MockStorage(private val context: Context) : MainContract.MainCommon {

  private val gson: Gson by lazy { Gson() }

  private fun getMockResponse(file: MockResponseFile): String =
    context.getJsonFromAssets(file)

  // --
  override suspend fun getPokemonList(offset: Int, limit: Int): PokemonList {
    try {
      val resultString = getMockResponse(MockResponseFile.POKEMON_LIST)
      return gson.fromJson(resultString, PokemonList::class.java)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return PokemonList(0, listOf(), null, null)
  }

  override suspend fun getPokemonDetail(id: Any): Pokemon {
    try {
      val resultString = getMockResponse(MockResponseFile.POKEMON_DETAIL)
      return gson.fromJson(resultString, Pokemon::class.java)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return Pokemon(0, "", listOf(), 0.0, 0.0,
      listOf(), listOf(), 0, PokeResult("", ""), null)
  }
}