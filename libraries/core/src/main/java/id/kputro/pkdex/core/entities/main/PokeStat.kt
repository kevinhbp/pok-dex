package id.kputro.pkdex.core.entities.main

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokeStat(
  @SerializedName("base_stat")
  val baseStat: Int,
  val effort: Int,
  val stat: PokeResult,
): Serializable