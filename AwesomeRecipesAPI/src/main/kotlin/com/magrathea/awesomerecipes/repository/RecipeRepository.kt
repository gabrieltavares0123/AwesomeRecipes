package com.magrathea.awesomerecipes.repository

import com.magrathea.awesomerecipes.model.Recipe
import org.springframework.data.repository.CrudRepository

interface RecipeRepository : CrudRepository<Recipe, Long> {
    fun findByTitle(title: String): List<Recipe>
}