package com.magrathea.awesomerecipes.di.module

import android.content.Context
import androidx.room.Room
import com.magrathea.awesomerecipes.data.local.RecipeDatabase
import com.magrathea.awesomerecipes.data.local.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDatabaseModule {

    @Provides
    @Singleton
    fun provideRecipeDatabase(@ApplicationContext applicationContext: Context): RecipeDatabase {
        val database = Room.databaseBuilder(
            applicationContext,
            RecipeDatabase::class.java,
            "RecipeDatabase"
        ).build()
        // Populate with fake initial data.
        database.populateInitialData()

        return database
    }

    @Provides
    fun provideRecipeDAO(recipeDatabase: RecipeDatabase): RecipeDao {
        return recipeDatabase.recipeDao()
    }
}