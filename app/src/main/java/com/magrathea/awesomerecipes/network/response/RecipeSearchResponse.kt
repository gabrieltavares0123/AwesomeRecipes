package com.magrathea.awesomerecipes.network.response

import com.google.gson.annotations.SerializedName
import com.magrathea.awesomerecipes.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)
