package com.example.sachinchandil.newsapp.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sachinchandil.newsapp.modules.headlines.HeadLinesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.chandilsachin.prapisample.dagger.annotations.ViewModelKeyMap
import com.example.sachinchandil.newsapp.dagger.factories.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKeyMap(HeadLinesViewModel::class)
    abstract fun getPRViewModel(viewModel: HeadLinesViewModel): ViewModel
}