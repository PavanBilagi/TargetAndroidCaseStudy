package com.target.targetcasestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.R
import com.target.targetcasestudy.replaceFragment
import com.target.targetcasestudy.ui.deallist.DealListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(
            fragmentManager = supportFragmentManager,
            fragment = DealListFragment.newInstance(),
        )
    }
}
