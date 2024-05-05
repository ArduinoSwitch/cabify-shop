package com.cabify.commons.navigation.dialog

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.cabify.commons.R
import com.cabify.commons.navigation.ScreenResult
import com.cabify.commons.navigation.dialog.ui.DialogView
import com.cabify.commons.navigation.dialog.ui.InformativeDialogView
import kotlinx.coroutines.CoroutineScope
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.getViewModel

sealed class DialogData : Parcelable {
    abstract val title: Int?
    abstract val description: Int?
    abstract val positiveButton: Int?
    open val cancellable = false

    internal abstract fun createView(scope: CoroutineScope, fragment: DialogFragment): DialogView

    @Parcelize
    data class Informative(
        @StringRes override val title: Int?,
        @StringRes override val description: Int?,
        @StringRes override val positiveButton: Int? = R.string.dialog_positive_button,
        override val cancellable: Boolean = true,
    ) : DialogData() {
        override fun createView(scope: CoroutineScope, fragment: DialogFragment) =
            InformativeDialogView(fragment.getViewModel(), this, scope, fragment)
    }
}

abstract class DialogWithResult<T : ScreenResult> : DialogData() {
    abstract val resultKey: String
}
