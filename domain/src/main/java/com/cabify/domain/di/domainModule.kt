package com.cabify.domain.di

import com.cabify.domain.store.GetStoreItemsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetStoreItemsUseCase(get(), get()) }
}
