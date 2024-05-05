package com.cabify.commons.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabify.commons.di.FrontDispatchers
import kotlinx.coroutines.plus

open class BaseViewModel(dispatchers: FrontDispatchers) : ViewModel() {
    val scope = viewModelScope + dispatchers.ui
}
