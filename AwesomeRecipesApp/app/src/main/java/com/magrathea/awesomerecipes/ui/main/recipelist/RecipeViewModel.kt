package com.magrathea.awesomerecipes.ui.main.recipelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipesRepository: RecipeRepository
) : ViewModel() {

    val userIntent = Channel<RecipesListIntents>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<RecipesListStates>(RecipesListStates.Idle)
    val state: StateFlow<RecipesListStates> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it) {
                    is RecipesListIntents.FetchRecipes -> fetchRecipes()
                }
            }
        }
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            _state.value = RecipesListStates.Loading
            _state.value = try {
                RecipesListStates.Recipes(recipesRepository.allRecipes())
            } catch (e: Exception) {
                RecipesListStates.Error(e?.localizedMessage)
            }
        }
    }
}