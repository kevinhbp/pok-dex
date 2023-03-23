package id.kputro.pkdex.core.entities.main

import java.io.Serializable

data class PokemonList(
  val count: Int,
  val results: List<PokeResult>,
  val next: String?,
  val previous: String?,
): Serializable