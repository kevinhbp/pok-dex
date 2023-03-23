package id.kputro.pkdex.ui.screen.main.widgets

import androidx.cardview.widget.CardView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import id.kputro.pkdex.core.entities.main.PokeResult
import id.kputro.pkdex.ui.components.TextView
import id.kputro.pkdex.ui.theme.ComponentShape
import id.kputro.pkdex.ui.theme.Spacing
import id.kputro.pkdex.ui.theme.Typography
import kotlinx.coroutines.flow.Flow

@Composable
fun PokemonList(
  pokemonList: Flow<PagingData<PokeResult>>
) {
  val pokemonListItems: LazyPagingItems<PokeResult> = pokemonList.collectAsLazyPagingItems()

  LazyColumn {
    items(pokemonListItems) { item ->
      item?.let {
        PokemonView(it)
      }
    }
  }
}

@Composable
fun PokemonView(
  pr: PokeResult
) {
  val shape = ComponentShape.card
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(50.dp)
      .padding(
        horizontal = Spacing.contentHorizontal,
        vertical = Spacing.x1,
      ),
    shape = shape
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      TextView(text = pr.name, style = Typography.bodySmall)
    }
  }
}