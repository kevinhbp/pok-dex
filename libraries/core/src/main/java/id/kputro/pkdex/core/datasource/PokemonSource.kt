package id.kputro.pkdex.core.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.kputro.pkdex.core.entities.main.PokeResult
import id.kputro.pkdex.core.network.clients.main.MainClient
import retrofit2.HttpException
import java.io.IOException

class PokemonSource(private val mainClient: MainClient) : PagingSource<Int, PokeResult>() {

  override fun getRefreshKey(state: PagingState<Int, PokeResult>): Int? {
    return state.anchorPosition
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeResult> {
    return try {
      val limit = params.loadSize
      val page = params.key ?: 1
      val offset = limit * (page - 1)
      val pokeList = mainClient.getPokemonList(offset, limit)
      val data = pokeList.results
      val prevKey = if (page == 1) null else (page - 1)
      val nextKey = if (data.isEmpty()) null else (page + 1)
      LoadResult.Page(data = data, prevKey = prevKey, nextKey = nextKey)
    } catch (e: IOException) {
      return LoadResult.Error(e)
    } catch (e: HttpException) {
      return LoadResult.Error(e)
    }
  }
}