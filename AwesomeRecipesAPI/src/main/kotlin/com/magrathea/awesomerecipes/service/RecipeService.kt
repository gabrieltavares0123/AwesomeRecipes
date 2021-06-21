package com.magrathea.awesomerecipes.service

import com.magrathea.awesomerecipes.model.Recipe
import com.magrathea.awesomerecipes.repository.RecipeRepository
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository
) {
    fun findAll(): List<Recipe> {
        return recipeRepository.findAll().toList()
    }

    fun findByTitle(title: String): List<Recipe> {
        return recipeRepository.findByTitle(title)
    }

    fun add(recipe: Recipe): Recipe {
        return recipeRepository.save(recipe)
    }

    fun edit(id: Long, recipe: Recipe): Recipe {
        val safeRecipe = recipe.copy(id = id)
        return recipeRepository.save(safeRecipe)
    }

    fun delete(id: Long) {
        recipeRepository.deleteById(id)
    }

    fun exists(id: Long): Boolean {
        return recipeRepository.existsById(id)
    }
}