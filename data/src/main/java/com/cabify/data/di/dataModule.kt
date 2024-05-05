package com.cabify.data.di

import com.cabify.data.BuildConfig
import com.cabify.data.network.ApiProvider
import com.cabify.data.network.interceptors.NetworkConnectivityInterceptor
import com.cabify.data.network.store.StoreDataSourceImpl
import com.cabify.domain.store.StoreDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory<StoreDataSource> { StoreDataSourceImpl(get()) }

    single { ApiProvider.provideApi(retrofit = get()) }
    single {
        ApiProvider.provideOkHttpClient(
            loggingInterceptor = get(),
            networkConnectivityInterceptor = get(),
        )
    }
    single { ApiProvider.provideLoggingInterceptor(BuildConfig.DEBUG) }
    single { ApiProvider.provideRetrofit(okHttpClient = get()) }
    single { NetworkConnectivityInterceptor(androidContext()) }
}
