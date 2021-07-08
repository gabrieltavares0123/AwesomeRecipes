package com.magrathea.awesomerecipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magrathea.awesomerecipes.data.local.dao.RecipeDao
import com.magrathea.awesomerecipes.data.local.model.Recipe
import com.magrathea.awesomerecipes.data.local.typeconverter.StringListConverter

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    private val recipes = listOf(
        Recipe(
            title = "recipe 0",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 1",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 2",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 3",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 4",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 5",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 6",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 7",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 8",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        ),
        Recipe(
            title = "recipe 9",
            featuredImage = "https://picsum.photos/200/300",
            ingredients = listOf("potato", "more potato", "sauce", "peeper", "salt"),
            preparation = listOf("boil potato with salt", "after throw peeper and sauce")
        )
    )

    fun populateInitialData() {
        runInTransaction {
            for (i in 0 until 10) {
                var recipe = recipes[i].copy()
                recipeDao().insert(recipe)
            }
        }
    }
}