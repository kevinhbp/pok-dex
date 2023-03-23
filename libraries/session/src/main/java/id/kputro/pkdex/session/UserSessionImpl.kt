package id.kputro.pkdex.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.squareup.moshi.Moshi
import id.kputro.pkdex.core.entities.login.LoginResponse
import id.kputro.pkdex.session.security.AESUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSessionImpl(
  private val context: Context,
  private val moshi: Moshi,
) : UserSession {
  private val adapter by lazy { moshi.adapter(LoginResponse::class.java) }
  private val Context.dataStore by preferencesDataStore("app_preferences")

  companion object {
    private const val KEY_USER_STRING = "key_user"
    val KEY_USER = stringPreferencesKey(AESUtils.encrypt(KEY_USER_STRING))
  }

  override suspend fun saveUser(loginResponse: LoginResponse) {
    context.dataStore.edit { prefs ->
      prefs[KEY_USER] = AESUtils.encrypt(adapter.toJson(loginResponse))
    }
  }

  override suspend fun deleteUser() {
    context.dataStore.edit { prefs -> prefs.remove(KEY_USER) }
  }

  override fun getUser(): Flow<LoginResponse?> = context.dataStore.data.map {
    val userString = it[KEY_USER] ?: return@map null
    adapter.fromJson(AESUtils.decrypt(userString))
  }

  override fun getToken(): Flow<String> = getUser().map { "Bearer " + it?.token }
}