package id.kputro.pkdex.session

import android.content.Context
import com.squareup.moshi.Moshi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object SessionKoinModules {
  val userSessionModule = module {
    fun provideUserSession(context: Context, moshi: Moshi): UserSession =
      UserSessionImpl(context, moshi)

    single { provideUserSession(androidContext(), get()) }
  }
}