/*
 * Created by Kevin Putro on 01/03/23 08:11
 * Copyright (c) Kevin Putro for APP Sinarmas 2023.
 * All rights reserved.
 */
package id.kputro.pkdex

import androidx.multidex.MultiDexApplication
import id.kputro.pkdex.core.di.GeneralKoinModules
import id.kputro.pkdex.session.SessionKoinModules
import id.kputro.pkdex.ui.screen.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MainApplication : MultiDexApplication() {

  private fun startKoinModules() {
    startKoin {
      androidLogger(Level.ERROR)
      androidContext(this@MainApplication)

      modules(
        listOf(
          GeneralKoinModules.persistenceModule,
          SessionKoinModules.userSessionModule,
          GeneralKoinModules.networkModule,
          GeneralKoinModules.repositoryModule,
          useCaseModule,
          viewModelModule,
        )
      )
    }
  }

  private val useCaseModule = module {  }

  private val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
  }

  override fun onCreate() {
    super.onCreate()
    startKoinModules()
  }
}