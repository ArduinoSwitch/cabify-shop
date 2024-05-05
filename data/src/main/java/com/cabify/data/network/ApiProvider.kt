package com.cabify.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.cabify.data.network.interceptors.NetworkConnectivityInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://gist.githubusercontent.com/palcalde/"

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

object ApiProvider {

    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkConnectivityInterceptor: NetworkConnectivityInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(networkConnectivityInterceptor)
        .connectTimeout(TIME_CONNECTION, TimeUnit.SECONDS)
        .readTimeout(TIME_CONNECTION, TimeUnit.SECONDS)
        .writeTimeout(TIME_CONNECTION, TimeUnit.SECONDS)
        .build()

    fun provideLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor().apply {
        level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }
}

private const val TIME_CONNECTION = 10L
