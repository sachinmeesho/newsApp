package com.example.sachinchandil.newsapp

import android.app.Activity
import android.app.Application
import com.example.sachinchandil.newsapp.dagger.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)
            .build()
            .inject(this)
    }


}