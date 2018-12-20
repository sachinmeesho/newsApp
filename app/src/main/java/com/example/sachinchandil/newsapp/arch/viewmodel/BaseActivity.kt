package com.example.sachinchandil.newsapp.arch.viewmodel

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    protected lateinit var mViewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, mFactory)[getViewModelType()]
    }

    abstract fun getViewModelType(): Class<T>
}