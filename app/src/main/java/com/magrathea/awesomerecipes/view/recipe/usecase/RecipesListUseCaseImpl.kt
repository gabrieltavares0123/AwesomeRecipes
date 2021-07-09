package com.magrathea.awesomerecipes.view.recipe.usecase

import com.magrathea.awesomerecipes.domain.model.Recipe
import com.magrathea.awesomerecipes.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesListUseCaseImpl @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val token: String
) : RecipesListUseCase {

    override suspend fun fetchRecipes(): Flow<List<Recipe>> {
        return recipeRepository.search(token, 1, "")
    }
}