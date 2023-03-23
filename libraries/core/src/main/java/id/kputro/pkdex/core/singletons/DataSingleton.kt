package id.kputro.pkdex.core.singletons

import id.kputro.pkdex.core.network.constants.AppEnv

class DataSingleton {
  companion object {
    private lateinit var instance: DataSingleton
    fun getDefault(): DataSingleton {
      if (!::instance.isInitialized) {
        instance = DataSingleton()
      }
      return instance
    }
  }

  var currentAppEnv: AppEnv = AppEnv.PRODUCTION
}