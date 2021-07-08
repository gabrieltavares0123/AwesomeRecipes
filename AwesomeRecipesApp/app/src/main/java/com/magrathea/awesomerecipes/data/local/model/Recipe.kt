package com.magrathea.awesomerecipes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.magrathea.awesomerecipes.data.local.typeconverter.StringListConverter

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String? = null,
    val publisher: String? = null,
    val featuredImage: String? = null,
    val rating: Int? = 0,
    val sourceUrl: String? = null,
    val ingredients: List<String>? = emptyList(),
    val preparation: List<String>? = emptyList()
)
