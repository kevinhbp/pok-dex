package id.kputro.pkdex.ui.screen.main.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.kputro.pkdex.R
import id.kputro.pkdex.core.extension.ColorPokemon
import id.kputro.pkdex.ui.theme.Spacing

object ImagePokemonType {
  private const val normal = R.drawable.ic_normal
  private const val fire = R.drawable.ic_fire
  private const val fighting = R.drawable.ic_fighting
  private const val water = R.drawable.ic_water
  private const val flying = R.drawable.ic_flying
  private const val grass = R.drawable.ic_grass
  private const val poison = R.drawable.ic_poison
  private const val electric = R.drawable.ic_electric
  private const val ground = R.drawable.ic_ground
  private const val psychic = R.drawable.ic_psychic
  private const val rock = R.drawable.ic_rock
  private const val ice = R.drawable.ic_ice
  private const val bug = R.drawable.ic_bug
  private const val dragon = R.drawable.ic_dragon
  private const val ghost = R.drawable.ic_ghost
  private const val dark = R.drawable.ic_dark
  private const val steel = R.drawable.ic_steel
  private const val fairy = R.drawable.ic_fairy


  private fun toMap(): Map<String, Int> {
    return mapOf(
      Pair("normal", normal),
      Pair("fire", fire),
      Pair("fighting", fighting),
      Pair("water", water),
      Pair("flying", flying),
      Pair("grass", grass),
      Pair("poison", poison),
      Pair("electric", electric),
      Pair("ground", ground),
      Pair("psychic", psychic),
      Pair("rock", rock),
      Pair("ice", ice),
      Pair("bug", bug),
      Pair("dragon", dragon),
      Pair("ghost", ghost),
      Pair("dark", dark),
      Pair("steel", steel),
      Pair("fairy", fairy),
    )
  }

  fun get(propertyName: String): Int {
    val mapRep = toMap()
    var result: Int? = null
    if (mapRep.containsKey(propertyName)) {
      result = mapRep[propertyName]
    }
    if (result == null) {
      result = normal
    }
    return result
  }
}

@Composable
fun PokemonTypeIcon(type: String, size: Int, modifier: Modifier = Modifier) {
  val bgColor = ColorPokemon.get(type)
  Box(
    modifier = modifier.background(
      color = bgColor,
      shape = CircleShape
    ),
  ) {
    Image(
      painter = painterResource(id = ImagePokemonType.get(type)),
      contentDescription = type,
      modifier = Modifier
        .size(size.dp)
        .padding(Spacing.x1)
    )
  }
}