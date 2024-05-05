package com.cabify.commons.di

import com.cabify.commons.navigation.Navigator
import com.cabify.commons.navigation.dialog.ui.InformativeDialogViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val commonsModule = module {

    single { Navigator() }
    viewModel { InformativeDialogViewModel(
        dispatchers = get(),
    ) }

    single {
        AppDispatchers(
            Dispatchers.Main.immediate,
            Dispatchers.Default,
            Dispatchers.IO,
        )
    }.binds(arrayOf(FrontDispatchers::class, DomainDispatcher::class, BackDispatchers::class))
}
