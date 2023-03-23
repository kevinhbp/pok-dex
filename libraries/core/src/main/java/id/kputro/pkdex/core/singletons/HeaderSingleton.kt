package id.kputro.pkdex.core.singletons

class HeaderSingleton {
  companion object {
    private lateinit var instance: HeaderSingleton
    fun getDefault(): HeaderSingleton {
      if (!::instance.isInitialized) {
        instance = HeaderSingleton()
      }
      return instance
    }
  }

  var authorization: String = ""
  var deviceId: String = ""
}