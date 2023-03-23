package id.kputro.pkdex.core.entities.main

import java.io.Serializable

data class PokeType(
  val slot: Int,
  val type: PokeResult,
): Serializable