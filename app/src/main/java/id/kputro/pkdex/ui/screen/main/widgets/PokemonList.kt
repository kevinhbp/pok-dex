package id.kputro.pkdex.ui.screen.main.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import id.kputro.pkdex.core.entities.main.PokeResult
import id.kputro.pkdex.ui.components.TextView
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
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = Spacing.x2, vertical = Spacing.x1)
  ) {
    TextView(text = pr.name, style = Typography.bodySmall)
  }
}