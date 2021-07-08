package com.magrathea.awesomerecipes.ui.main.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {
    companion object {
        fun newInstance() = RecipeListFragment()
    }

    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ComposeView(requireContext())
        return view.apply {
            setContent {
                RecipesListLayout().Inflate()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            recipeViewModel.state.collect {
                when(it) {
                    is RecipesListStates.Idle -> {

                    }

                    is RecipesListStates.Loading -> {

                    }

                    is RecipesListStates.Recipes -> {

                    }

                    is RecipesListStates.Error -> {

                    }

                    else -> {  }
                }
            }
        }
    }
}