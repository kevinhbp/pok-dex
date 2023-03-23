package id.kputro.pkdex.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.kputro.pkdex.ui.theme.*

enum class ButtonType {
  PRIMARY, SECONDARY, TERTIARY, CTA,
  PRIMARY_DESTRUCTIVE, SECONDARY_DESTRUCTIVE, TERTIARY_DESTRUCTIVE;
}

@Immutable
private class OurButtonColors(
  private val type: ButtonType,
  private val darkMode: Boolean
) : ButtonColors {

  private fun getContainerColor(): Color {
    if (darkMode) {
      return when (type) {
        ButtonType.CTA -> ColorComponentDark.buttonCTA
        ButtonType.TERTIARY -> ColorComponentDark.buttonTertiary
        ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponentDark.buttonTertiary
        ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponentDark.buttonSecondaryDestructive
        ButtonType.SECONDARY -> ColorComponentDark.buttonSecondary
        ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponentDark.buttonPrimaryDestructive
        ButtonType.PRIMARY -> ColorComponentDark.buttonPrimary
      }
    }
    return when (type) {
      ButtonType.CTA -> ColorComponent.buttonCTA
      ButtonType.TERTIARY -> ColorComponent.buttonTertiary
      ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponent.buttonTertiary
      ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponent.buttonSecondaryDestructive
      ButtonType.SECONDARY -> ColorComponent.buttonSecondary
      ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponent.buttonPrimaryDestructive
      ButtonType.PRIMARY -> ColorComponent.buttonPrimary
    }
  }

  private fun getDisabledContainerColor(): Color {
    if (darkMode) {
      return when (type) {
        ButtonType.CTA -> ColorComponentDark.buttonCTAInactive
        ButtonType.TERTIARY -> ColorComponentDark.buttonTertiary
        ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponentDark.buttonTertiary
        ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponentDark.buttonSecondaryInactive
        ButtonType.SECONDARY -> ColorComponentDark.buttonSecondaryInactive
        ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponentDark.buttonPrimaryInactive
        ButtonType.PRIMARY -> ColorComponentDark.buttonPrimaryInactive
      }
    }

    return when (type) {
      ButtonType.CTA -> ColorComponent.buttonCTAInactive
      ButtonType.TERTIARY -> ColorComponent.buttonTertiary
      ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponent.buttonTertiary
      ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponent.buttonSecondaryInactive
      ButtonType.SECONDARY -> ColorComponent.buttonSecondaryInactive
      ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponent.buttonPrimaryInactive
      ButtonType.PRIMARY -> ColorComponent.buttonPrimaryInactive
    }
  }

  private fun getContentColor(): Color {
    if (darkMode) {
      return when (type) {
        ButtonType.CTA -> ColorComponentDark.onButtonCTA
        ButtonType.TERTIARY -> ColorComponentDark.onButtonTertiary
        ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponentDark.onButtonTertiaryDestructive
        ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponentDark.onButtonSecondaryDestructive
        ButtonType.SECONDARY -> ColorComponentDark.onButtonSecondary
        ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponentDark.onButtonPrimaryDestructive
        ButtonType.PRIMARY -> ColorComponentDark.onButtonPrimary
      }
    }
    return when (type) {
      ButtonType.CTA -> ColorComponent.onButtonCTA
      ButtonType.TERTIARY -> ColorComponent.onButtonTertiary
      ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponent.onButtonTertiaryDestructive
      ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponent.onButtonSecondaryDestructive
      ButtonType.SECONDARY -> ColorComponent.onButtonSecondary
      ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponent.onButtonPrimaryDestructive
      ButtonType.PRIMARY -> ColorComponent.onButtonPrimary
    }
  }

  private fun getDisabledContentColor(): Color {
    if (darkMode) {
      return when (type) {
        ButtonType.CTA -> ColorComponentDark.onButtonCTAInactive
        ButtonType.TERTIARY -> ColorComponentDark.onButtonTertiaryInactive
        ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponentDark.onButtonTertiaryInactive
        ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponentDark.onButtonSecondaryInactive
        ButtonType.SECONDARY -> ColorComponentDark.onButtonSecondaryInactive
        ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponentDark.onButtonPrimaryInactive
        ButtonType.PRIMARY -> ColorComponentDark.onButtonPrimaryInactive
      }
    }
    return when (type) {
      ButtonType.CTA -> ColorComponent.onButtonCTAInactive
      ButtonType.TERTIARY -> ColorComponent.onButtonTertiaryInactive
      ButtonType.TERTIARY_DESTRUCTIVE -> ColorComponent.onButtonTertiaryInactive
      ButtonType.SECONDARY_DESTRUCTIVE -> ColorComponent.onButtonSecondaryInactive
      ButtonType.SECONDARY -> ColorComponent.onButtonSecondaryInactive
      ButtonType.PRIMARY_DESTRUCTIVE -> ColorComponent.onButtonPrimaryInactive
      ButtonType.PRIMARY -> ColorComponent.onButtonPrimaryInactive
    }
  }

  @Composable
  override fun backgroundColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) getContainerColor() else getDisabledContainerColor())
  }

  @Composable
  override fun contentColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) getContentColor() else getDisabledContentColor())
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || this::class != other::class) return false

    other as OurButtonColors

    if (type != other.type) return false

    return true
  }

  override fun hashCode(): Int {
    var result = getContainerColor().hashCode()
    result = 31 * result + getContentColor().hashCode()
    result = 31 * result + getDisabledContainerColor().hashCode()
    result = 31 * result + getDisabledContentColor().hashCode()
    return result
  }
}

@Suppress("unused")
object OurButtonDefaults {
  private val RegularButtonHorizontalPadding = Spacing.x5
  private val RegularButtonVerticalPadding = Spacing.x4
  private val SmallButtonHorizontalPadding = Spacing.x4
  private val SmallButtonVerticalPadding = Spacing.x2

  val RegularPadding = PaddingValues(
    start = RegularButtonHorizontalPadding,
    top = RegularButtonVerticalPadding,
    end = RegularButtonHorizontalPadding,
    bottom = RegularButtonVerticalPadding
  )

  val SmallPadding = PaddingValues(
    start = SmallButtonHorizontalPadding,
    top = SmallButtonVerticalPadding,
    end = SmallButtonHorizontalPadding,
    bottom = SmallButtonVerticalPadding
  )

  val None = PaddingValues(
    all = 0.dp
  )

}

@Composable
fun ButtonPrimary(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp,
  ),
  shape: Shape = ComponentShape.button,
  border: BorderStroke? = null,
  contentPadding: PaddingValues = OurButtonDefaults.RegularPadding,
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    contentPadding = contentPadding,
    colors = OurButtonColors(ButtonType.PRIMARY, isSystemInDarkTheme())
  ) {
    Text(text = text, style = Typography.labelMedium)
  }
}

@Composable
fun ButtonSecondary(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp,
  ),
  shape: Shape = ComponentShape.button,
  border: BorderStroke? = null,
  contentPadding: PaddingValues = OurButtonDefaults.RegularPadding,
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    contentPadding = contentPadding,
    colors = OurButtonColors(ButtonType.SECONDARY, isSystemInDarkTheme())
  ) {
    Text(text = text, style = Typography.labelMedium)
  }
}

@Composable
fun ButtonTertiary(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp,
  ),
  shape: Shape = ComponentShape.button,
  border: BorderStroke? = null,
  contentPadding: PaddingValues = OurButtonDefaults.RegularPadding,
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    contentPadding = contentPadding,
    colors = OurButtonColors(ButtonType.TERTIARY, isSystemInDarkTheme())
  ) {
    Text(text = text, style = Typography.labelMedium)
  }
}

@Composable
fun ButtonPrimaryDestructive(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp,
  ),
  shape: Shape = ComponentShape.button,
  border: BorderStroke? = null,
  contentPadding: PaddingValues = OurButtonDefaults.RegularPadding,
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    contentPadding = contentPadding,
    colors = OurButtonColors(ButtonType.PRIMARY_DESTRUCTIVE, isSystemInDarkTheme())
  ) {
    Text(text = text, style = Typography.labelMedium)
  }
}

@Composable
fun ButtonSecondaryDestructive(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp,
  ),
  shape: Shape = ComponentShape.button,
  border: BorderStroke? = null,
  contentPadding: PaddingValues = OurButtonDefaults.RegularPadding,
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    contentPadding = contentPadding,
    colors = OurButtonColors(ButtonType.SECONDARY_DESTRUCTIVE, isSystemInDarkTheme())
  ) {
    Text(text = text, style = Typography.labelMedium)
  }
}

@Composable
fun ButtonTertiaryDestructive(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp,
  ),
  shape: Shape = ComponentShape.button,
  border: BorderStroke? = null,
  contentPadding: PaddingValues = OurButtonDefaults.RegularPadding,
) {
  Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    contentPadding = contentPadding,
    colors = OurButtonColors(ButtonType.TERTIARY_DESTRUCTIVE, isSystemInDarkTheme())
  ) {
    Text(text = text, style = Typography.labelMedium)
  }
}

@Preview(showBackground = true)
@Composable
fun ButtonDefaultPreview() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        top = Spacing.x5,
        bottom = Spacing.x5,
        start = Spacing.x3,
        end = Spacing.x3,
      )
      .verticalScroll(rememberScrollState())
  ) {
    ButtonPrimary(
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
    ButtonSecondary(
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
    ButtonTertiary(
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ButtonDestructivePreview() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        top = Spacing.x5,
        bottom = Spacing.x5,
        start = Spacing.x3,
        end = Spacing.x3,
      )
      .verticalScroll(rememberScrollState())
  ) {
    ButtonPrimaryDestructive(
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
    ButtonSecondaryDestructive(
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
    ButtonTertiaryDestructive(
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ButtonDisabledPreview() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        top = Spacing.x5,
        bottom = Spacing.x5,
        start = Spacing.x3,
        end = Spacing.x3,
      )
      .verticalScroll(rememberScrollState())
  ) {
    ButtonPrimary(
      enabled = false,
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
    ButtonSecondary(
      enabled = false,
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
    ButtonTertiary(
      enabled = false,
      onClick = {},
      text = "Login",
      modifier = Modifier.fillMaxWidth(),
    )
  }
}