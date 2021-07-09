package com.magrathea.awesomerecipes.view.recipe.usecase

import com.magrathea.awesomerecipes.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesListUseCase {
    suspend fun fetchRecipes(): Flow<List<Recipe>>
}