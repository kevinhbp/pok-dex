package id.kputro.pkdex.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.kputro.pkdex.core.BuildConfig
import id.kputro.pkdex.core.network.clients.ticu.TicuClient
import id.kputro.pkdex.core.network.clients.ticu.TicuEndpoints
import id.kputro.pkdex.core.network.constants.AppEnv
import id.kputro.pkdex.core.network.interceptors.HttpRequestInterceptor
import id.kputro.pkdex.core.repositories.AuthRepository
import id.kputro.pkdex.core.repositories.MockStorage
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun getOkHttpClient(context: Context): OkHttpClient {
  val logInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  val builder = OkHttpClient.Builder()
    .addInterceptor(logInterceptor)
    .addInterceptor(HttpRequestInterceptor())
    .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
    .protocols(listOf(Protocol.HTTP_1_1))
    .retryOnConnectionFailure(true)
    .connectTimeout(5, TimeUnit.SECONDS)
    .readTimeout(5, TimeUnit.SECONDS)
    .writeTimeout(5, TimeUnit.SECONDS)

  if (BuildConfig.DEBUG) builder.addInterceptor(
    ChuckerInterceptor.Builder(context)
      .collector(ChuckerCollector(context))
      .maxContentLength(250000L)
      .redactHeaders(emptySet())
      .alwaysReadResponseBody(false)
      .build()
  )
  return builder.build()
}

private fun getRetrofit(client: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
  .client(client).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
  .build()

private fun getMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

private fun getTicuService(retrofit: Retrofit): TicuEndpoints =
  retrofit.create(TicuEndpoints::class.java)

private fun getTicuClient(production: TicuEndpoints, staging: TicuEndpoints): TicuClient =
  TicuClient(production, staging)

private fun getMockStorage(context: Context): MockStorage = MockStorage(context)

/*private fun getTypeConverter(moshi: Moshi) : TypeConverter = TypeConverter(moshi)

private fun getAppDatabase(context: Context, typeConverter: TypeConverter): AppDatabase =
  Room.databaseBuilder(context, AppDatabase::class.java, "App.db")
    .fallbackToDestructiveMigration()
    .addTypeConverter(typeConverter)
    .build()*/
///
///
///
object GeneralKoinModules {
  val networkModule = module {
    single { getOkHttpClient(androidContext()) }
    single { getMockStorage(androidContext()) }

    // PRODUCTION
    single(named(AppEnv.PRODUCTION)) { getRetrofit(get(), BuildConfig.URL_PRD) }
    single(named(AppEnv.PRODUCTION)) { getTicuService(get(named(AppEnv.PRODUCTION))) }

    // STAGING
    single(named(AppEnv.STAGING)) { getRetrofit(get(), BuildConfig.URL_STG) }
    single(named(AppEnv.STAGING)) { getTicuService(get(named(AppEnv.STAGING))) }

    single { getTicuClient(get(named(AppEnv.PRODUCTION)), get(named(AppEnv.STAGING))) }
  }
  val persistenceModule = module {
    single { getMoshi() }

  }
  val repositoryModule = module {
    fun getAuthRepository(client: TicuClient, mockStorage: MockStorage): AuthRepository =
      AuthRepository(client, mockStorage)

    single { getAuthRepository(get(), get()) }
  }
}