package com.cabify.feature.splash

import android.os.Bundle
import android.view.View
import com.cabify.commons.ui.BaseFragment
import com.cabify.commons.ui.utils.observe
import com.cabify.feature.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.animation) {
            if (it.not()) {
                viewModel.navToDashboard()
            }
        }
    }
}
