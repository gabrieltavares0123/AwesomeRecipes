package com.magrathea.awesomerecipes.ui.main.recipelist

import com.magrathea.awesomerecipes.data.local.dao.RecipeDao
import com.magrathea.awesomerecipes.data.local.model.Recipe
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override fun insertRecipe(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    override fun allRecipes(): List<Recipe>? {
        return recipeDao.all()
    }

    override fun recipesByTitle(title: String): List<Recipe>? {
        return recipeDao.byTitle(title)
    }
}