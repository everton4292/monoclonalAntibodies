package com.everton.mononuclealanticoorps.network

import android.icu.util.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideRetrofit(): Retrofit {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okhttp = OkHttpClient.Builder()
        .connectTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()

    return Retrofit.Builder()
        .baseUrl("https://monoclonaldatabase.firebaseio.com/.json")
        .client(okhttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
