package com.magrathea.awesomerecipes.di

import com.magrathea.awesomerecipes.repository.RecipeRepository
import com.magrathea.awesomerecipes.view.recipe.usecase.RecipesListUseCase
import com.magrathea.awesomerecipes.view.recipe.usecase.RecipesListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideRecipesListUseCase(recipeRepository: RecipeRepository, @Named("auth_token") token: String): RecipesListUseCase {
        return RecipesListUseCaseImpl(recipeRepository = recipeRepository, token = token)
    }
}