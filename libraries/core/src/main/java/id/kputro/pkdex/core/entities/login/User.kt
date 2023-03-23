package id.kputro.pkdex.core.entities.login

import java.io.Serializable

data class User(
  val name: String,
  val role: String,
) : Serializable {

  override fun toString(): String = "{name: $name, role: $role}"
}