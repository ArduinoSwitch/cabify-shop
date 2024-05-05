package com.cabify.commons.ui.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Utils to collect a flow in a cleaner and safe way from a Fragment.
 *
 * It enforces observing viewLifecycleOwner to avoid bugs due to Fragments' view lifecycle not
 * being tied to the enclosing Fragment's lifecycle
 */
inline fun <T> Fragment.observe(flow: Flow<T>, crossinline observer: (T) -> Unit) {
    viewLifecycleOwner.observeFromOwner(flow, observer)
}

/**
 * Utils to collect a flow from a LifecycleOwner.
 * Prefer using the fragment or activity extension if possible
 */
inline fun <T> LifecycleOwner.observeFromOwner(flow: Flow<T>, crossinline observer: (T) -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect { observer(it) }
        }
    }
}
