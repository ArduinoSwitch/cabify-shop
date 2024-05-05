package com.cabify.shop

import android.app.Application
import com.cabify.commons.di.commonsModule
import com.cabify.data.di.dataModule
import com.cabify.domain.di.domainModule
import com.cabify.feature.di.featureModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import timber.log.Timber

class CabifyShopApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupDi()
    }

    private fun setupDi() {
        GlobalContext.startKoin {
            androidContext(this@CabifyShopApplication)
            commonsModule.single { applicationScope }
            modules(
                commonsModule,
                domainModule,
                dataModule,
                featureModule
            )
        }
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) Timber.plant(LineNumberDebugTree())
    }
}

private class LineNumberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}
