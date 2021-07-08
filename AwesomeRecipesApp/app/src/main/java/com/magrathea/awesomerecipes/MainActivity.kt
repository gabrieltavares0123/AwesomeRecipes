package com.magrathea.awesomerecipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.magrathea.awesomerecipes.ui.main.recipelist.RecipeListFragment
import com.magrathea.awesomerecipes.util.FragmentUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity /*@Inject*/ constructor(
    /*private val fragmentUtil: FragmentUtil*/
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val fragment = RecipeListFragment.newInstance()
        fragmentUtil.replaceFragmentWithoutHistory(
            fragment = fragment,
            fragmentActivity =  this,
            containerId = R.id.nav_host_fragment
        )*/
    }
}