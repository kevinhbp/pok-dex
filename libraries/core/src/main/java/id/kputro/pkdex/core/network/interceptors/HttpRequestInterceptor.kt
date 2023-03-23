package id.kputro.pkdex.core.network.interceptors

import id.kputro.pkdex.core.BuildConfig
import id.kputro.pkdex.core.singletons.HeaderSingleton
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalMethod = originalRequest.method
    val originalBody = originalRequest.body
    val mBuilder = originalRequest.newBuilder()

    val appName = BuildConfig.APP_NAME
    val appVersionName = BuildConfig.VERSION_NAME
    val appVersionCode = BuildConfig.VERSION_CODE
    mBuilder
      .header("App-Name", appName)
      .header("App-Version-Name", appVersionName)
      .header("App-Version-Code", appVersionCode)
    val authorization = HeaderSingleton.getDefault().authorization
    val deviceId = HeaderSingleton.getDefault().deviceId
    if (authorization.isNotEmpty()) {
      mBuilder.header("Authorization", authorization)
    }
    if (deviceId.isNotEmpty()) {
      mBuilder.header("Device-Id", deviceId)
    }
    val newRequest = mBuilder.method(originalMethod, originalBody).build()
    return chain.proceed(newRequest)
  }
}