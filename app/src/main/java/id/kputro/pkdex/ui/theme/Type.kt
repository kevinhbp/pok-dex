package id.kputro.pkdex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.kputro.pkdex.R

private val MaisonNeueExt = FontFamily(
  Font(R.font.maisonneue_ext_book, weight = FontWeight.Normal),
  Font(R.font.maisonneue_ext_bold, weight = FontWeight.Bold),
  Font(R.font.maisonneue_ext_demi, weight = FontWeight.SemiBold),
)

val Typography = Typography(
  displayLarge = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    lineHeight = 44.sp,
  ),
  displayMedium = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 36.sp,
  ),
  displaySmall = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Bold,
    fontSize = 21.sp,
    lineHeight = 28.sp,
  ),
  headlineLarge = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
  ),
  headlineMedium = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 20.sp,
  ),
  headlineSmall = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
  ),
  titleLarge = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
  ),
  titleMedium = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 20.sp,
  ),
  titleSmall = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
  ),
  labelLarge = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 20.sp,
  ),
  labelMedium = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.3.sp
  ),
  labelSmall = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp
  ),
  bodyLarge = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.sp,
  ),
  bodyMedium = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.2.sp
  ),
  bodySmall = TextStyle(
    fontFamily = MaisonNeueExt,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp
  ),
)