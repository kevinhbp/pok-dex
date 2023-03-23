package id.kputro.pkdex.core.entities.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class LoginParameter(
  @SerializedName("cuis_id", alternate = ["cuisId"])
  var cuisId: String,
  var password: String,
  @SerializedName("location_id")
  var locationId: String
) : Serializable