plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  compileSdk = AppConfig.compileSdk

  defaultConfig {
    applicationId = AppConfig.appId
    minSdk = AppConfig.minSdk
    targetSdk = AppConfig.targetSdk
    versionCode = AppConfig.versionCode
    versionName = AppConfig.versionName

    testInstrumentationRunner = AppConfig.androidTestInstrumentation
    vectorDrawables.useSupportLibrary = true
    multiDexEnabled = true
    setProperty(AppConfig.archivesBaseName, AppConfig.getApkFileName())

    buildConfigField("String", "APP_NAME", "\"${AppConfig.appName}\"")
    buildConfigField("String", "VERSION_NAME", "\"${AppConfig.versionName}\"")
    buildConfigField("String", "VERSION_CODE", "\"${AppConfig.versionCode}\"")
  }

  buildTypes {
    debug {
      isDebuggable = true
      isMinifyEnabled = false
      versionNameSuffix = "-arch"

      resValue("string", "app_name", "${AppConfig.appName} Dev")
    }
    release {
      isDebuggable = false
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
      )

      resValue("string", "app_name", "${AppConfig.appName}")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = "11"
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
    }
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.composeVersion
  }
  packagingOptions {
    resources.excludes.add("META-INF/*")
    jniLibs {
      pickFirsts.add("**/*.so")
    }
  }

  splits {
    abi {
      isEnable = true
      reset()
      include("armeabi-v7a")
      isUniversalApk = false
    }
  }
}

subprojects {
  project.configurations.all {
    resolutionStrategy.eachDependency {
      if (requested.group == "androidx.core" && !requested.name.contains("androidx")) {
        useVersion(Versions.appcompat)
      }
    }
    resolutionStrategy.force("org.antlr:antlr4-runtime:4.7.1")
    resolutionStrategy.force("org.antlr:antlr4-tool:4.7.1")
  }
}

dependencies {
  implementationProject(ModuleDependencies.libraries)
  implementationProject(ModuleDependencies.session)
  // implementationProject(ModuleDependencies.features)

  implementation(AppDependencies.androidLibs)
  implementation(AppDependencies.dependencyInjectionComposeLibraries)
  implementation(AppDependencies.networkLibraries)
  implementation(AppDependencies.persistenceLibraries)
  implementation(AppDependencies.navigationLibraries)

  kapt(AppDependencies.kaptLibraries)
  testImplementation(AppDependencies.testLibraries)
  androidTestImplementation(AppDependencies.androidTestLibraries)
  implementationDebug(AppDependencies.debugLibs)
}