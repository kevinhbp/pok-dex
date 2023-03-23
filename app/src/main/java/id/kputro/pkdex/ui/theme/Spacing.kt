package id.kputro.pkdex.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Spacing {
  val x1 = 4.dp
  val x2 = (2 * 4).dp
  val x3 = (3 * 4).dp
  val x4 = (4 * 4).dp
  val x5 = (5 * 4).dp
  val x6 = (6 * 4).dp
  val x7 = (7 * 4).dp
  val x8 = (8 * 4).dp
  val x9 = (9 * 4).dp
  val x10 = (10 * 4).dp
  val x11 = (11 * 4).dp
  val x12 = (12 * 4).dp

  ///
  val contentHorizontal = x6

  fun get(times: Int) : Dp = (4 * times).dp

  fun getNegative(times: Int): Dp = (-1 * 4 * times).dp
}