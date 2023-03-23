package id.kputro.pkdex.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import id.kputro.pkdex.core.extension.debouncer
import id.kputro.pkdex.ui.theme.*
import kotlinx.coroutines.MainScope

@Immutable
private class OurTextFieldColor(darkMode: Boolean) : TextFieldColors {
  companion object {
    internal const val AnimationDuration = 150
  }

  private val textColor = if (darkMode) ColorText.white1 else ColorText.black1
  private val disabledTextColor = if (darkMode) ColorText.gray3 else ColorText.gray2

  private val cursorColor = if (darkMode) ColorTertiary.c70 else ColorTertiary.c40
  private val errorCursorColor =
    if (darkMode) ColorComponentDark.errorPrimary else ColorComponent.errorPrimary

  private val focusedIndicatorColor: Color = if (darkMode) ColorTertiary.c70 else ColorTertiary.c40
  private val unfocusedIndicatorColor: Color = if (darkMode) ColorText.gray1 else ColorText.gray2
  private val errorIndicatorColor: Color = if (darkMode) ColorText.red3 else ColorText.red2
  private val disabledIndicatorColor: Color =
    if (darkMode) ColorComponentDark.surface2 else ColorComponent.surface1

  private val leadingIconColor: Color =
    if (darkMode) ColorComponentDark.inputIcon else ColorComponent.inputIcon
  private val disabledLeadingIconColor: Color =
    if (darkMode) ColorComponentDark.inputIconInactive else ColorComponent.inputIconInactive
  private val errorLeadingIconColor: Color =
    if (darkMode) ColorComponentDark.inputIconError else ColorComponent.inputIconError
  private val trailingIconColor: Color =
    if (darkMode) ColorComponentDark.inputIcon else ColorComponent.inputIcon
  private val disabledTrailingIconColor: Color =
    if (darkMode) ColorComponentDark.inputIconInactive else ColorComponent.inputIconInactive
  private val errorTrailingIconColor: Color =
    if (darkMode) ColorComponentDark.inputIconError else ColorComponent.inputIconError

  private val backgroundColor: Color =
    if (darkMode) ColorComponentDark.surface2 else ColorComponent.surface1
  private val disabledBackgroundColor: Color =
    if (darkMode) ColorComponentDark.surface1 else ColorComponent.surface3

  private val focusedLabelColor: Color =
    if (darkMode) ColorTertiary.c70 else ColorTertiary.c40
  private val unfocusedLabelColor: Color =
    if (darkMode) ColorText.gray3 else ColorText.gray2
  private val disabledLabelColor: Color =
    if (darkMode) ColorText.gray2 else ColorText.gray3
  private val errorLabelColor: Color =
    if (darkMode) ColorText.red3 else ColorText.red2

  private val placeholderColor: Color =
    if (darkMode) ColorText.gray3 else ColorText.gray1
  private val disabledPlaceholderColor: Color =
    if (darkMode) ColorText.gray1 else ColorText.gray3

  @Composable
  override fun textColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(
      if (enabled) textColor else disabledTextColor
    )
  }

  @Composable
  override fun backgroundColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(
      if (enabled) backgroundColor else disabledBackgroundColor
    )
  }

  @Composable
  override fun cursorColor(isError: Boolean): State<Color> {
    return rememberUpdatedState(
      if (isError) errorCursorColor else cursorColor
    )
  }

  @Composable
  override fun placeholderColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(
      if (enabled) placeholderColor else disabledPlaceholderColor
    )
  }

  @Composable
  override fun labelColor(
    enabled: Boolean,
    error: Boolean,
    interactionSource: InteractionSource
  ): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    val targetValue = when {
      !enabled -> disabledLabelColor
      error -> errorLabelColor
      focused -> focusedLabelColor
      else -> unfocusedLabelColor
    }
    return rememberUpdatedState(targetValue)
  }

  @Composable
  override fun indicatorColor(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource
  ): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    val targetValue = when {
      !enabled -> disabledIndicatorColor
      isError -> errorIndicatorColor
      focused -> focusedIndicatorColor
      else -> unfocusedIndicatorColor
    }
    return if (enabled) {
      animateColorAsState(targetValue, tween(durationMillis = AnimationDuration))
    } else {
      rememberUpdatedState(targetValue)
    }
  }

  @Composable
  override fun leadingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
    return rememberUpdatedState(
      if (enabled) {
        leadingIconColor
      } else if (isError) {
        errorLeadingIconColor
      } else {
        disabledLeadingIconColor
      }
    )
  }

  @Composable
  override fun trailingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
    return rememberUpdatedState(
      if (enabled) {
        trailingIconColor
      } else if (isError) {
        errorTrailingIconColor
      } else {
        disabledTrailingIconColor
      }
    )
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || this::class != other::class) return false

    other as OurTextFieldColor

    if (textColor != other.textColor) return false
    if (disabledTextColor != other.disabledTextColor) return false
    if (cursorColor != other.cursorColor) return false
    if (errorCursorColor != other.errorCursorColor) return false
    if (focusedIndicatorColor != other.focusedIndicatorColor) return false
    if (unfocusedIndicatorColor != other.unfocusedIndicatorColor) return false
    if (errorIndicatorColor != other.errorIndicatorColor) return false
    if (disabledIndicatorColor != other.disabledIndicatorColor) return false
    if (leadingIconColor != other.leadingIconColor) return false
    if (disabledLeadingIconColor != other.disabledLeadingIconColor) return false
    if (errorLeadingIconColor != other.errorLeadingIconColor) return false
    if (trailingIconColor != other.trailingIconColor) return false
    if (disabledTrailingIconColor != other.disabledTrailingIconColor) return false
    if (errorTrailingIconColor != other.errorTrailingIconColor) return false
    if (backgroundColor != other.backgroundColor) return false
    if (focusedLabelColor != other.focusedLabelColor) return false
    if (unfocusedLabelColor != other.unfocusedLabelColor) return false
    if (disabledLabelColor != other.disabledLabelColor) return false
    if (errorLabelColor != other.errorLabelColor) return false
    if (placeholderColor != other.placeholderColor) return false
    if (disabledPlaceholderColor != other.disabledPlaceholderColor) return false

    return true
  }

  override fun hashCode(): Int {
    var result = textColor.hashCode()
    result = 31 * result + disabledTextColor.hashCode()
    result = 31 * result + cursorColor.hashCode()
    result = 31 * result + errorCursorColor.hashCode()
    result = 31 * result + focusedIndicatorColor.hashCode()
    result = 31 * result + unfocusedIndicatorColor.hashCode()
    result = 31 * result + errorIndicatorColor.hashCode()
    result = 31 * result + disabledIndicatorColor.hashCode()
    result = 31 * result + leadingIconColor.hashCode()
    result = 31 * result + disabledLeadingIconColor.hashCode()
    result = 31 * result + errorLeadingIconColor.hashCode()
    result = 31 * result + trailingIconColor.hashCode()
    result = 31 * result + disabledTrailingIconColor.hashCode()
    result = 31 * result + errorTrailingIconColor.hashCode()
    result = 31 * result + backgroundColor.hashCode()
    result = 31 * result + focusedLabelColor.hashCode()
    result = 31 * result + unfocusedLabelColor.hashCode()
    result = 31 * result + disabledLabelColor.hashCode()
    result = 31 * result + errorLabelColor.hashCode()
    result = 31 * result + placeholderColor.hashCode()
    result = 31 * result + disabledPlaceholderColor.hashCode()
    return result
  }
}

@Composable
fun GlobalIdInput(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  isError: Boolean = false,
) {
  val focusManager = LocalFocusManager.current
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    readOnly = readOnly,
    label = {
      Text(text = "Global ID", style = Typography.labelMedium)
    },
    isError = isError,
    keyboardActions = KeyboardActions(onAny = {
      focusManager.clearFocus()
    }),
    keyboardOptions = KeyboardOptions(
      autoCorrect = false,
      imeAction = ImeAction.Done
    ),
    singleLine = true,
    shape = ComponentShape.input,
    colors = OurTextFieldColor(isSystemInDarkTheme()),
    textStyle = Typography.labelMedium,
  )
}

@Composable
fun PasswordInput(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  isError: Boolean = false,
) {
  val focusManager = LocalFocusManager.current
  var passwordVisible by rememberSaveable { mutableStateOf(false) }
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    readOnly = readOnly,
    label = {
      Text(text = "Password", style = Typography.labelMedium)
    },
    isError = isError,
    keyboardActions = KeyboardActions(onAny = {
      focusManager.clearFocus()
    }),
    keyboardOptions = KeyboardOptions(
      autoCorrect = false,
      imeAction = ImeAction.Done
    ),
    singleLine = true,
    shape = ComponentShape.input,
    colors = OurTextFieldColor(isSystemInDarkTheme()),
    textStyle = Typography.labelMedium,
    visualTransformation =
    if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    trailingIcon = {
      val image = if (passwordVisible) {
        Icons.Default.Visibility
      } else {
        Icons.Default.VisibilityOff
      }

      val description = if (passwordVisible) "Hide password" else "Show password"

      IconButton(onClick = {
        passwordVisible = !passwordVisible
        debouncer(waitMs = 3000, coroutineScope = MainScope()) {
          if (passwordVisible) {
            passwordVisible = false
          }
        }
      }) {
        Icon(imageVector = image, description)
      }
    }
  )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SpinnerInput(
  label: String,
  options: MutableList<String>,
  modifier: Modifier = Modifier,
  preSelectedValue: String = "",
  enabled: Boolean = true,
  isError: Boolean = false,
    onValueChange: (index: Int, value: String) -> Unit,
) {
  var expanded by rememberSaveable { mutableStateOf(false) }
  var selectedValue by remember { mutableStateOf(preSelectedValue) }
  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded },
  ) {
    OutlinedTextField(
      value = selectedValue,
      onValueChange = { },
      modifier = modifier,
      enabled = enabled,
      readOnly = true,
      isError = isError,
      label = {
        Text(text = label, style = Typography.labelMedium)
      },
      shape = ComponentShape.input,
      colors = OurTextFieldColor(isSystemInDarkTheme()),
      textStyle = Typography.labelMedium,
      trailingIcon = {
        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
      }
    )
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
    ) {
      options.forEachIndexed { index, option ->
        DropdownMenuItem(onClick = {
          selectedValue = option
          expanded = false
          onValueChange(index, option)
        }) {
          Text(text = option, style = Typography.bodyMedium)
        }
      }
    }
  }
}