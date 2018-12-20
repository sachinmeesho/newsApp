package com.example.sachinchandil.newsapp.dagger.components

import android.app.Application
import com.example.sachinchandil.newsapp.NewsApplication
import com.example.sachinchandil.newsapp.dagger.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ApiModule::class, RepositoryModule::class, ViewModelModule::class,
        ActivityModule::class, DatabaseModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        /*@BindsInstance
        fun context(application: Application): Builder*/
        fun build(): AppComponent
    }

    fun inject(application: NewsApplication)
}