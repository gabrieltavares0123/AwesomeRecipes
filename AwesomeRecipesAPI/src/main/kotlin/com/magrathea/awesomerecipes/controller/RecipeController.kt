package com.magrathea.awesomerecipes.controller

import com.magrathea.awesomerecipes.model.Recipe
import com.magrathea.awesomerecipes.repository.RecipeRepository
import com.magrathea.awesomerecipes.service.RecipeService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("recipes")
class RecipeController(
    private val recipeService: RecipeService
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Recipe>> {
        val allRecipes = recipeService.findAll()
        return ResponseEntity.ok(allRecipes)
    }

    @GetMapping("/title")
    fun findByTitle(@RequestBody title: String): ResponseEntity<List<Recipe>> {
        val allByTitle = recipeService.findByTitle(title)
        return ResponseEntity.ok(allByTitle)
    }

    @PostMapping
    fun add(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val newRecipe = recipeService.add(recipe)
        return ResponseEntity.ok(newRecipe)
    }

    @PutMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        if (recipeService.exists(id)) {
            val editedRecipe = recipeService.edit(id, recipe)
            return ResponseEntity.ok(editedRecipe)
        }

        return ResponseEntity.notFound().build()
    }

    @DeleteMapping
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        if (recipeService.exists(id)) {
            recipeService.delete(id)
            return ResponseEntity.ok().build()
        }

        return ResponseEntity.notFound().build()
    }
}