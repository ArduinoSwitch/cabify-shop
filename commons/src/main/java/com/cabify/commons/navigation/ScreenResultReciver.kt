package com.cabify.commons.navigation

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

interface ScreenResultReceiver {
    val screenResultKeys: List<String>
    fun onResult(key: String, result: ScreenResult)
}

interface ScreenResult : Parcelable

fun Fragment.delegateDialogResults(receiver: ScreenResultReceiver) {
    findNavController().currentBackStackEntry?.savedStateHandle?.let { savedStateHandle ->
        receiver.screenResultKeys.forEach { resultKey ->
            savedStateHandle.getLiveData<ScreenResult>(resultKey).observe(viewLifecycleOwner) {
                if (it != null) {
                    savedStateHandle[resultKey] = null
                    receiver.onResult(resultKey, it)
                }
            }
        }
    }
}

interface NavHostHolder {
    val navHostId: Int
}
