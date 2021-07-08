package com.magrathea.awesomerecipes.view.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magrathea.awesomerecipes.view.recipe.usecase.RecipesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesListUseCase: RecipesListUseCase
) : ViewModel() {

    // Store the UI user intents.
    var uiIntent = MutableSharedFlow<RecipesListIntent>()

    // Store the UI states.
    private var _uiState = MutableStateFlow<RecipesListState>(RecipesListState.Nothing)
    val uiState: StateFlow<RecipesListState> get() = _uiState

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            uiIntent.collect {
                when(it) {
                    is RecipesListIntent.FetchRecipes -> {
                        fetchRecipes()
                    }
                    is RecipesListIntent.DoNothing -> {
                        doNothing()
                    }
                }
            }
        }
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            try {
                recipesListUseCase.fetchRecipes().collect { recipes ->
                    _uiState.value = RecipesListState.Loading

                    if (recipes.isEmpty()) {
                        _uiState.value = RecipesListState.Empty(message = "Recipes list is empty.")
                    } else {
                        _uiState.value = RecipesListState.Success(data = recipes)
                    }
                }
            } catch (e: Exception) {
                RecipesListState.Error(throwable = e)
            }
        }
    }

    private fun doNothing() {
        _uiState.value = RecipesListState.Nothing
    }
}