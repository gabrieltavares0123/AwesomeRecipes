package com.magrathea.awesomerecipes.di

import com.google.gson.GsonBuilder
import com.magrathea.awesomerecipes.domain.mapper.DomainMapper
import com.magrathea.awesomerecipes.domain.model.Recipe
import com.magrathea.awesomerecipes.network.BASE_URL
import com.magrathea.awesomerecipes.network.model.RecipeDto
import com.magrathea.awesomerecipes.network.model.RecipeDtoMapper
import com.magrathea.awesomerecipes.network.service.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRecipeMapper(): DomainMapper<RecipeDto, Recipe> {
        return RecipeDtoMapper()
    }

    @Provides
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)
    }

    @Provides
    @Named("auth_token")
    fun provideToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}