package com.magrathea.awesomerecipes.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Recipe(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    @ElementCollection
    val ingredients: List<String> = emptyList(),
    @ElementCollection
    val preparation: List<String> = emptyList()
)
