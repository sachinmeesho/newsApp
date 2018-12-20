package com.example.sachinchandil.newsapp.dagger.modules

import com.example.sachinchandil.newsapp.modules.headlines.HeadLinesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributePrActivity(): HeadLinesActivity
}