package com.magrathea.awesomerecipes.di

import com.magrathea.awesomerecipes.domain.mapper.DomainMapper
import com.magrathea.awesomerecipes.domain.model.Recipe
import com.magrathea.awesomerecipes.network.model.RecipeDto
import com.magrathea.awesomerecipes.network.service.RecipeService
import com.magrathea.awesomerecipes.repository.RecipeRepository
import com.magrathea.awesomerecipes.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        domainMapper: DomainMapper<RecipeDto, Recipe>): RecipeRepository {
        return RecipeRepositoryImpl(
            recipeService = recipeService,
            domainMapper = domainMapper)
    }
}