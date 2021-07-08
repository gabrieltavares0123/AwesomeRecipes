package com.magrathea.awesomerecipes.view.recipe

sealed class RecipesListIntent {
    object FetchRecipes : RecipesListIntent()
    object DoNothing : RecipesListIntent()
}