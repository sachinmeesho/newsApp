package com.example.sachinchandil.newsapp.dagger.components

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}