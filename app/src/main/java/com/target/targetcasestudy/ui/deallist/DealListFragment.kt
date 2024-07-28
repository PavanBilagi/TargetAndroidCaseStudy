package com.target.targetcasestudy.ui.deallist

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.target.targetcasestudy.replaceFragment
import com.target.targetcasestudy.ui.base.BaseComposeFragment
import com.target.targetcasestudy.ui.dealdetails.DealDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class DealListFragment : BaseComposeFragment() {

    private val viewModel: DealListViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun ProvideComposeContent() {
        val state = viewModel.dealListStateFlow.collectAsStateWithLifecycle().value
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("List") },
                )
            }
        ) { innerPadding ->
            DealListScreen(modifier = Modifier.padding(innerPadding),
                state = state,
                dealClicked = { dealId ->
                    replaceFragment(
                        fragmentManager = (context as AppCompatActivity).supportFragmentManager,
                        fragment = DealDetailsFragment.newInstance(dealId),
                        addToBackStack = true
                    )
                }
            )
        }
    }

    companion object {
        fun newInstance() = DealListFragment()
    }
}
