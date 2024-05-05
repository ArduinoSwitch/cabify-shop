package com.cabify.commons.navigation.dialog

import com.cabify.commons.navigation.ScreenResult
import kotlinx.parcelize.Parcelize

abstract class DialogResult : ScreenResult {
    @Parcelize
    object Positive : DialogResult()
}
