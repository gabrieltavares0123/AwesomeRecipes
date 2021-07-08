package com.magrathea.awesomerecipes.ui.main.recipelist

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.magrathea.awesomerecipes.data.local.model.Recipe

class RecipesListLayout {
    @Preview
    @Composable
    fun Inflate() {
        Scaffold {
            RecipeListItem(
                Recipe(
                    title = "recipe 0",
                    featuredImage = "https://picsum.photos/200/300",
                    ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
                    preparation = listOf("boil potato with salt", "after throw peeper and sauce")
                )
            )
        }
    }

    @Composable
    fun RecipeListItem(
        recipe: Recipe
    ) {
    }
}