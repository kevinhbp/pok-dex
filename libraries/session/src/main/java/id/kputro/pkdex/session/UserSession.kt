package id.kputro.pkdex.session

import id.kputro.pkdex.core.entities.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserSession {
  suspend fun saveUser(loginResponse: LoginResponse)
  suspend fun deleteUser()
  fun getUser(): Flow<LoginResponse?>
  fun getToken(): Flow<String>
}