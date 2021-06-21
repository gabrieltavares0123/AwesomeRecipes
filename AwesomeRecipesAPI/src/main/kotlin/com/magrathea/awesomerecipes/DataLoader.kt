package com.magrathea.awesomerecipes

import com.magrathea.awesomerecipes.model.Recipe
import com.magrathea.awesomerecipes.repository.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class DataLoader(
    private val recipeRepository: RecipeRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        recipeRepository.saveAll(listOf(
            Recipe(title = "frango empanado", description = "frango empanado"),
            Recipe(title = "frango assado", description = "assa o frango"),
            Recipe(title = "coxinha", description = "faz a massa, enche com recheio e assa"),
            )
        )
    }
}