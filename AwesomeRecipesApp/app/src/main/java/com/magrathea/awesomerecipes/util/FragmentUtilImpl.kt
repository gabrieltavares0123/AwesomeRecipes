package com.magrathea.awesomerecipes.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FragmentUtilImpl : FragmentUtil {

    override fun replaceFragmentWithHistory(fragmentActivity: FragmentActivity, fragment: Fragment, containerId: Int) {
        fragmentActivity.supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment)
            .addToBackStack("Added fragment")
            .commit()
    }

    override fun replaceFragmentWithoutHistory(fragmentActivity: FragmentActivity, fragment: Fragment, containerId: Int) {
        fragmentActivity.supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment)
            .commit()
    }
}