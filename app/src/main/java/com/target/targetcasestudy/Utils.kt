package com.target.targetcasestudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun replaceFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    frameId: Int = R.id.container,
    addToBackStack: Boolean = false,
    tag: String? = null
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    transaction.replace(frameId, fragment, tag)
    if (addToBackStack) {
        transaction.addToBackStack(tag)
    }
    transaction.commit()
}
