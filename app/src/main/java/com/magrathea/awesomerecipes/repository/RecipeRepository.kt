package com.magrathea.awesomerecipes.repository

import com.magrathea.awesomerecipes.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): Flow<List<Recipe>>
    suspend fun get(token: String, id: Long): Flow<Recipe>
}