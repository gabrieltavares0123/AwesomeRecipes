package com.magrathea.awesomerecipes.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject
import javax.inject.Named

class FragmentUtilImpl @Inject constructor(
    @Named("container_id") @IdRes private val containerId: Int
) : FragmentUtil {

    override fun replaceFragmentWithHistory(fragmentActivity: FragmentActivity, fragment: Fragment) {
        fragmentActivity.supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment)
            .addToBackStack("Added fragment")
            .commit()
    }

    override fun replaceFragmentWithoutHistory(fragmentActivity: FragmentActivity, fragment: Fragment) {
        fragmentActivity.supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment)
            .commit()
    }
}