package com.example.democexup.di

import android.app.Application
import com.example.democexup.domain.repository.DemoRepository
import com.example.democexup.local.Dao.DatabaseDemo
import com.example.democexup.ui.DemoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin{
            androidContext(this@App)
            modules(listOf(databaseModule,repoModule,viewModelModule))
        }
    }

    private val databaseModule = module {
        single{ DatabaseDemo.getInstance(androidContext())}
    }


    private val repoModule = module {
        single { DemoRepository(get())}
    }

    private val viewModelModule = module{
        viewModel { DemoViewModel(get()) }
    }
}