package com.magrathea.awesomerecipes.di.module

import com.magrathea.awesomerecipes.ui.main.recipelist.RecipeRepository
import com.magrathea.awesomerecipes.ui.main.recipelist.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RecipeViewModelModule {
    @Provides
    fun providesRecipeRepository(recipeRepositoryImpl: RecipeRepositoryImpl): RecipeRepository {
        return recipeRepositoryImpl
    }
}