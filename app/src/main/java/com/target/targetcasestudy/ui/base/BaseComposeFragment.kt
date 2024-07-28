package com.target.targetcasestudy.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.ui.ui.theme.MainAppTheme

abstract class BaseComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MainAppTheme {
                    ProvideComposeContent()
                }
            }
        }
    }

    @Composable
    abstract fun ProvideComposeContent()
}