@file:Suppress("SameParameterValue")

package id.kputro.pkdex.core.repositories

import android.content.Context
import com.google.gson.Gson
import id.kputro.pkdex.core.entities.region.GetRegionResponse
import id.kputro.pkdex.core.entities.region.Region
import id.kputro.pkdex.core.network.clients.ticu.TicuContract
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
  REGIONS("get_regions.json")
}

class MockStorage(private val context: Context) : TicuContract.TicuCommon {

  private val gson: Gson by lazy { Gson() }

  private fun getMockResponse(file: MockResponseFile): String =
    context.getJsonFromAssets(file)

  // --
  override suspend fun getRegions(): List<Region> {
    try {
      val resultString = getMockResponse(MockResponseFile.REGIONS)
      val result: GetRegionResponse = gson.fromJson(resultString, GetRegionResponse::class.java)
      return result.data.orEmpty()
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return listOf()
  }
}