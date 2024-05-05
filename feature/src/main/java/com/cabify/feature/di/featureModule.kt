package com.cabify.feature.di

import com.cabify.feature.shop.ShopViewModel
import com.cabify.feature.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {

    viewModel {
        ShopViewModel(
            get(),
            get(),
            get(),
        )
    }

    viewModel {
        SplashViewModel(
            get(),
            get(),
        )
    }
}
