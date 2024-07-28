package com.target.targetcasestudy.ui.dealdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.target.targetcasestudy.ui.base.BaseComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DealDetailsFragment : BaseComposeFragment() {

    private val viewModel: DealDetailsViewModel by viewModel {
        parametersOf(requireArguments().getString(EXTRA_DEAL_ID))
    }


    @Composable
    override fun ProvideComposeContent() {
        val state = viewModel.dealStateFlow.collectAsStateWithLifecycle().value
        DealDetailsScreen(
            state = state,
            onBackPressed = { parentFragmentManager.popBackStackImmediate() }
        )
    }

    companion object {
        private const val EXTRA_DEAL_ID = "dealId"
        fun newInstance(dealId: String) = DealDetailsFragment().apply {
            arguments = bundleOf(EXTRA_DEAL_ID to dealId)
        }
    }
}
