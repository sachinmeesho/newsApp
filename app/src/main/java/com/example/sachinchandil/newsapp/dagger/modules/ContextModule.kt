package com.example.sachinchandil.newsapp.dagger.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var application: Application) {


    @Provides
    fun providesContext(): Application {
        return application
    }
}