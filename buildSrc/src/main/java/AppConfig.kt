import java.text.SimpleDateFormat
import java.util.*

//app level config constants
object AppConfig {
    const val compileSdk = 33
    const val minSdk = 23
    const val targetSdk = 33
    const val versionCode = 100000
    const val versionName = "1.00"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules =  "consumer-rules.pro"
    const val dimension = "environment"

    const val appId = "id.kputro.pkdex"
    const val appName = "Pokédex"

    const val archivesBaseName = "archivesBaseName"
    // --
    const val BASE_URL_PRD = "https://pokeapi.co/api/v2/"
    const val BASE_URL_STG = "https://pokeapi.co/api/v2/"

    fun getBuildTime(): String {
        return SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
    }

    fun getApkFileName(): String {
        return "pokedex_ver.$versionName.$versionCode.${getBuildTime()}"
    }
}