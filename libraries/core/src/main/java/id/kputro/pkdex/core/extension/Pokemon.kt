package id.kputro.pkdex.core.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

fun String.formatName(): String {
  if (this.isEmpty()) return ""
  if (!this.contains(' ')) {
    return this.capitalize(Locale.current)
  }
  val names = this.split(' ')
  val result = StringBuffer()
  for (mName in names) {
    result.append(mName.capitalize(Locale.current))
  }
  return result.toString()
}

fun Int.formatId(): String {
  return this.toString().padStart(3, '0')
}

fun getPokeSpritesById(id: Int): String {
  return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
}

object ColorPokemon {
  private val normal = Color(0xffdedede)
  private val fire = Color(0xFFFF7847)
  private val fighting = Color(0xFFEB4971)
  private val water = Color(0xff66b9ff)
  private val flying = Color(0xFFA197E1)
  private val grass = Color(0xff3fd456)
  private val poison = Color(0xffca58ec)
  private val electric = Color(0xFFFFDB63)
  private val ground = Color(0xFFA2735E)
  private val psychic = Color(0xFFfb9d9a)
  private val rock = Color(0xffbbb5a8)
  private val ice = Color(0xFF91d9cd)
  private val bug = Color(0xFFA2B257)
  private val dragon = Color(0xFF6526F1)
  private val ghost = Color(0xffb3b9d9)
  private val dark = Color(0xff535356)
  private val steel = Color(0xff688588)
  private val fairy = Color(0xFFf1a6eb)


  private fun toMap(): Map<String, Color> {
    val result: Map<String, Color> = mapOf()
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

  fun get(propertyName: String): Color {
    val mapRep = toMap()
    var result: Color? = null
    if (mapRep.containsKey(propertyName)) {
      result = mapRep[propertyName]
    }
    if (result == null) {
      result = normal
    }
    return result
  }
}

object ColorPokemonBackground {
  private val normal = Color(0xffd2d2d2)
  private val fire = Color(0xFFD5886D)
  private val fighting = Color(0xFFD9798B)
  private val water = Color(0xff78beff)
  private val flying = Color(0xFFC2BBEE)
  private val grass = Color(0xff95ce9e)
  private val poison = Color(0xffd695ee)
  private val electric = Color(0xFFD0AC38)
  private val ground = Color(0xFFF8CBB6)
  private val psychic = Color(0xffB6726F)
  private val rock = Color(0xffe1dfdd)
  private val ice = Color(0xffc8f5e8)
  private val bug = Color(0xFFB5C586)
  private val dragon = Color(0xFFB094EF)
  private val ghost = Color(0xffb8bacc)
  private val dark = Color(0xff9c9ca1)
  private val steel = Color(0xff9bb1b6)
  private val fairy = Color(0xfff1cfee)


  private fun toMap(): Map<String, Color> {
    val result: Map<String, Color> = mapOf()
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

  fun get(propertyName: String): Color {
    val mapRep = toMap()
    var result: Color? = null
    if (mapRep.containsKey(propertyName)) {
      result = mapRep[propertyName]
    }
    if (result == null) {
      result = normal
    }
    return result
  }
}