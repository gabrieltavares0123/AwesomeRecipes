package com.magrathea.awesomerecipes.view.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
@AndroidEntryPoint
class RecipesListFragment : Fragment() {

    private val recipesListViewModel: RecipesListViewModel by viewModels()

    private var currentState: RecipesListState = RecipesListState.Nothing

    init {
        lifecycleScope.launchWhenCreated {
            recipesListViewModel.uiState.collect { newState ->
                currentState = newState
                Log.d("RecipesListFragment", "App: New State received...")
            }
        }
    }

    companion object {
        fun newInstance() = RecipesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())

        return view.apply {
            setContent {
                App(recipesListViewModel = recipesListViewModel)
            }
        }
    }

    @Composable
    fun App(recipesListViewModel: RecipesListViewModel) {
        currentState.recipesListViewModel = recipesListViewModel

        MaterialTheme {
            Scaffold {
                currentState.BuildUI()
                Log.d("RecipesListFragment", "App: Applying new State...")
            }
        }
    }
}
