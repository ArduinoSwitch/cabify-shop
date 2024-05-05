package com.cabify.feature.splash

import com.cabify.commons.di.FrontDispatchers
import com.cabify.commons.navigation.Navigator
import com.cabify.commons.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    dispatcher: FrontDispatchers,
    private val navigator: Navigator,
): BaseViewModel(dispatcher) {
    /**
     * val to delay splash, maybe make some animation
     */
    val animation = MutableStateFlow(true)

    init {
        scope.launch {
            delay(1500)
            animation.value = false
        }
    }

    fun navToDashboard() {
        navigator.goTo(SplashFragmentDirections.navToMain())
    }
}
