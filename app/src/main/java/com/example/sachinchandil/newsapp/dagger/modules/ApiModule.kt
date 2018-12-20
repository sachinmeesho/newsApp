package com.example.sachinchandil.newsapp.dagger.modules

import android.app.Application
import android.content.Context
import com.example.sachinchandil.newsapp.BuildConfig
import com.example.sachinchandil.newsapp.NewsApplication
import com.example.sachinchandil.newsapp.R
import com.example.sachinchandil.newsapp.retrofit.NewsApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Inject
    fun providesGithubService(okHttpClient: OkHttpClient, factory: Converter.Factory): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_PATH)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    @Inject
    fun provideApiOkHttpClient(@Named("apiKey") apiKey: String): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(generateHttpLoggingInterceptor())
        }
        builder.addInterceptor {
            val original = it.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", apiKey)
                .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .url(url)
            it.proceed(requestBuilder.build())

        }
        return builder.build()
    }

    private fun generateHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Named("apiKey")
    fun provideApiKey(): String {
        return API_KEY
    }

    companion object {
        val BASE_PATH = "https://newsapi.org/v2/"
        val API_KEY = "ebe1cfc65d1e4dad8ac3d5d57c62a56f"
    }
}