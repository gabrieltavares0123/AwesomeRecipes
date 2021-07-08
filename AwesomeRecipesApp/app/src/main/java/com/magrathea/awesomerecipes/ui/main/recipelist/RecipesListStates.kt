package com.magrathea.awesomerecipes.ui.main.recipelist

import com.magrathea.awesomerecipes.data.local.model.Recipe

/**
 * Class to represent the UI State.
 */
sealed class RecipesListStates {
    object Idle : RecipesListStates()
    object Loading : RecipesListStates()
    class Recipes(val data: List<Recipe>?) : RecipesListStates()
    class Empty(val message: String) : RecipesListStates()
    class Error(val message: String) : RecipesListStates()
}