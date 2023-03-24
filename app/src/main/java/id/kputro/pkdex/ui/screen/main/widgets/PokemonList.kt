package id.kputro.pkdex.ui.screen.main.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import id.kputro.pkdex.R
import id.kputro.pkdex.core.entities.main.Pokemon
import id.kputro.pkdex.core.extension.*
import id.kputro.pkdex.ui.components.TextView
import id.kputro.pkdex.ui.theme.*
import kotlinx.coroutines.flow.Flow

@Composable
fun PokemonList(
  pokemonList: Flow<PagingData<Pokemon>>
) {
  val pokemonListItems: LazyPagingItems<Pokemon> = pokemonList.collectAsLazyPagingItems()
  LazyVerticalGrid(
    columns = GridCells.Fixed(2)
  ) {
    items(pokemonListItems.itemCount) { index ->
      pokemonListItems[index]?.let {
        PokemonView(index, it)
      }
    }

    when (pokemonListItems.loadState.refresh) {
      is LoadState.Loading -> {
        item {
          PokemonLoadingView(pokemonListItems.itemCount)
        }
      }
      is LoadState.Error -> {}
      is LoadState.NotLoading -> {}
    }

    when (pokemonListItems.loadState.append) {
      is LoadState.Loading -> {
        item {
          PokemonLoadingView(pokemonListItems.itemCount)
        }
      }
      is LoadState.Error -> {}
      is LoadState.NotLoading -> {}
    }
  }
}

@Composable
fun PokemonView(
  index: Int,
  pr: Pokemon,
  darkMode: Boolean = isSystemInDarkTheme(),
) {
  var padTop = Spacing.x2
  var padStart = Spacing.x1
  var padEnd = Spacing.x2

  if (index in 0..1) {
    padTop += Spacing.x2
  }
  if (index.isEven()) { // left
    padStart = Spacing.x2
    padEnd = Spacing.x1
  }

  val shape = ComponentShape.card
  var backgroundColor =
    if (darkMode) ColorComponentDark.cardContainer else ColorComponent.cardContainer

  var colorTextA = ColorText.black1
  var colorTextADark = ColorText.white1
  var colorTextB = ColorText.gray1
  var colorTextBDark = ColorText.gray3

  val spritesUrl = getPokeSpritesById(pr.id)
  if (pr.types.isNotEmpty()) {
    val firstType = pr.types[0].type.name
    backgroundColor = ColorPokemonBackground.get(firstType)
    colorTextA = ColorText.white1
    colorTextADark = ColorText.white1
    colorTextB = ColorText.white2
    colorTextBDark = ColorText.white2
  }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(240.dp)
      .padding(
        top = padTop,
        start = padStart,
        end = padEnd,
      ),
    shape = shape,
    backgroundColor = backgroundColor,
    elevation = 2.dp
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = Spacing.x2, vertical = Spacing.x1)
      ) {
        TextView(
          text = pr.name.formatName(), style = Typography.titleSmall,
          color = colorTextA, colorDarkMode = colorTextADark
        )
        Spacer(modifier = Modifier.weight(1f))
        TextView(
          text = pr.id.formatId(), style = Typography.bodySmall,
          color = colorTextB, colorDarkMode = colorTextBDark
        )
      }
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(spritesUrl)
          .crossfade(true)
          .build(),
        contentDescription = pr.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}

@Composable
fun PokemonLoadingView(
  index: Int,
  message: String = "Please wait",
  darkMode: Boolean = isSystemInDarkTheme(),
) {
  var padTop = Spacing.x1
  var padStart = Spacing.x2
  var padEnd = Spacing.x3

  if (index in 0..1) {
    padTop += Spacing.x3
  }
  if (index.isEven()) { // left
    padStart = Spacing.x3
    padEnd = Spacing.x2
  }

  val shape = ComponentShape.card
  val backgroundColor =
    if (darkMode) ColorComponentDark.cardContainer else ColorComponent.cardContainer
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_1))
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(240.dp)
      .padding(
        top = padTop,
        start = padStart,
        end = padEnd,
        bottom = Spacing.x1
      ),
    shape = shape,
    backgroundColor = backgroundColor,
    elevation = 2.dp
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column(
        modifier = Modifier.padding(horizontal = Spacing.x5),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        LottieAnimation(
          composition = composition,
          iterations = LottieConstants.IterateForever,
          modifier = Modifier.size(60.dp),
          contentScale = ContentScale.Fit,
        )
        TextView(
          message,
          style = Typography.labelMedium,
          modifier = Modifier
            .padding(bottom = Spacing.x2)
            .offset(y = -(Spacing.x2)),
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemViewPreview() {
  val pr = Pokemon.getBulbasaur()
  PokemonView(0, pr)
}