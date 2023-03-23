plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  compileSdk = AppConfig.compileSdk

  defaultConfig {
    minSdk = AppConfig.minSdk

    testInstrumentationRunner = AppConfig.androidTestInstrumentation
    consumerProguardFiles("consumer-rules.pro")

    buildConfigField("String", "APP_NAME", "\"${AppConfig.appName}\"")
    buildConfigField("String", "VERSION_NAME", "\"${AppConfig.versionName}\"")
    buildConfigField("String", "VERSION_CODE", "\"${AppConfig.versionCode}\"")

    buildConfigField("String", "URL_PRD", "\"${AppConfig.BASE_URL_PRD}\"")
    buildConfigField("String", "URL_STG", "\"${AppConfig.BASE_URL_STG}\"")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(AppDependencies.androidLibs)
  implementation(AppDependencies.dependencyInjectionLibraries)
  implementation(AppDependencies.networkLibraries)
  implementation(AppDependencies.persistenceLibraries)
  implementation(AppDependencies.navigationLibraries)

  kapt(AppDependencies.kaptLibraries)
  testImplementation(AppDependencies.testLibraries)
  androidTestImplementation(AppDependencies.androidTestLibraries)
  implementationDebug(AppDependencies.debugLibs)
}