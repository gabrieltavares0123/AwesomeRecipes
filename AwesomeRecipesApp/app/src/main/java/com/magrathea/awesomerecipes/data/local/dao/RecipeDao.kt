package com.magrathea.awesomerecipes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.magrathea.awesomerecipes.data.local.model.Recipe

@Dao
interface RecipeDao {
    @Insert
    fun insert(entity: Recipe)

    @Query("SELECT * FROM recipe")
    fun all(): List<Recipe>?

    @Query("SELECT * FROM recipe WHERE title LIKE :title")
    fun byTitle(title: String): List<Recipe>?
}