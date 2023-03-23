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
  implementationProject(ModuleDependencies.libraries)

  implementation(AppDependencies.androidLibs)
  implementation(AppDependencies.dependencyInjectionLibraries)
  implementation(AppDependencies.networkLibraries)
  implementation(AppDependencies.persistenceLibraries)
  implementation(AppDependencies.navigationLibraries)

  implementation(AppDependencies.datastoreLibraries)

  kapt(AppDependencies.kaptLibraries)
  testImplementation(AppDependencies.testLibraries)
  androidTestImplementation(AppDependencies.androidTestLibraries)
  implementationDebug(AppDependencies.debugLibs)
}