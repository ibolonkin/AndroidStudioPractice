package com.example.practice3

import android.app.Application
import com.example.practice3.di.filmsFeatureModule
import com.example.practice3.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Application: Application(){
    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@Application)
            modules(mainModule, filmsFeatureModule)
        }
    }
}