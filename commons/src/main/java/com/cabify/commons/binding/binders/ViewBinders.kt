package com.cabify.commons.binding.binders

import android.view.View
import android.widget.Button
import com.cabify.commons.binding.enableIf
import com.cabify.commons.binding.showIf
import kotlinx.coroutines.flow.Flow

interface ViewBinders: BaseBinder {
    fun View.bindIsVisible(isVisible: Flow<Boolean>) {
        observe(isVisible) {
            showIf(it)
        }
    }

    fun Button.bindEnabled(isEnabled: Flow<Boolean>) {
        observe(isEnabled) {
            enableIf(it)
        }
    }
}

