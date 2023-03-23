package id.kputro.pkdex.core.entities.login


import java.io.Serializable

data class LoginResponse(
  val token: String,
  val user: User
) : Serializable {

  override fun toString(): String = "{token: $token, user: $user}"
}