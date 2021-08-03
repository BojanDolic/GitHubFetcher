package com.electrocoder.githubfetcher.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClient {

    const val BASE_URL = "https://api.github.com"

    private val log = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)



    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(log)
        .build()



}