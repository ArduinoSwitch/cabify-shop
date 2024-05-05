package com.cabify.shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.cabify.commons.navigation.NavHostHolder
import com.cabify.commons.navigation.Navigator
import com.cabify.commons.navigation.Route
import com.cabify.commons.navigation.dialog.DialogData
import com.cabify.commons.utils.LogConstants
import com.cabify.feature.MainGraphDirections
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), NavHostHolder {
    override val navHostId: Int = R.id.nav_host

    private val navigator: Navigator by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        navigator.directions.onEach { route ->
            when (route) {
                is Route.Forward -> navigateTo(route.direction)
                is Route.Dialog -> openDialog(route.data)
                else -> navigateBack()
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateTo(route: NavDirections) {
        if (isFinishing) return
        Navigation.findNavController(this, R.id.nav_host).apply {
            navigate(route)
            logNavigation("Navigate to ${currentDestination?.getScreenName()}")
        }
    }

    private fun openDialog(data: DialogData) {
        if (isFinishing) return
        Navigation.findNavController(this, R.id.nav_host).apply {
            navigate(MainGraphDirections.openDialog(data))
            logNavigation("Open dialog ${currentDestination?.getScreenName()}")
        }
    }

    private fun navigateBack() {
        Navigation.findNavController(this, R.id.nav_host).apply {
            logNavigation("Navigate back ${currentDestination?.getScreenName()}")
        }.navigateUp()
    }

    private fun NavDestination?.getScreenName(): String {
        return this?.label?.toString() ?: "Unknown"
    }

    private fun logNavigation(message: String) {
        Timber.tag(LogConstants.NavigationEvent).d(message)
    }
}
