package com.magrathea.awesomerecipes.repository

import com.magrathea.awesomerecipes.domain.mapper.DomainMapper
import com.magrathea.awesomerecipes.domain.model.Recipe
import com.magrathea.awesomerecipes.network.model.RecipeDto
import com.magrathea.awesomerecipes.network.service.RecipeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeService: RecipeService,
    private val domainMapper: DomainMapper<RecipeDto, Recipe>
) : RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): Flow<List<Recipe>> {
        val result = recipeService.search(token = token, page = page, query = query).recipes
        return flowOf(domainMapper.toDomainList(initial = result))
    }

    override suspend fun get(token: String, id: Long): Flow<Recipe> {
        val result = recipeService.get(token = token, id = id)
        return flowOf(domainMapper.mapToDomainModel(model = result))
    }
}