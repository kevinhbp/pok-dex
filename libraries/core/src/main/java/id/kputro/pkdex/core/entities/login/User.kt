package id.kputro.pkdex.core.entities.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
  @SerializedName("user_id")
  val userId: String,
  val plantcode: String,
  val pernr: Long,
  val fullname: String,
  val role: String,
) : Serializable {

  override fun toString(): String =
    "{userId: $userId, plantCode: $plantcode, pernr: $pernr, fullname: $fullname, role: $role}"
}