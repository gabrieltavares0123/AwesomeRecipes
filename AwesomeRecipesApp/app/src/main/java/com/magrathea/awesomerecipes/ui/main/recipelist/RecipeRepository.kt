package com.magrathea.awesomerecipes.ui.main.recipelist

import com.magrathea.awesomerecipes.data.local.model.Recipe

interface RecipeRepository {
    fun insertRecipe(recipe: Recipe)
    fun allRecipes(): List<Recipe>?
    fun recipesByTitle(title: String): List<Recipe>?
}