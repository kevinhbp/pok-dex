package id.kputro.pkdex.core.entities.region

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Region(
  @SerializedName("code", alternate = ["domainCode"])
  val code: String,
  @SerializedName("name", alternate = ["domain"])
  val name: String,
) : Serializable {

  override fun toString(): String {
    return "Region: {code: $code, name: $name}"
  }
}