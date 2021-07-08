package com.magrathea.awesomerecipes.data.local.typeconverter

import androidx.room.TypeConverter
import java.io.Serializable

class StringListConverter : Serializable {
    private val separator: String = ";"

    @TypeConverter
    fun fromStringList(elements: List<String>?): String {
        return if (elements?.isEmpty() == true) {
            ""
        } else {
            elements?.forEach { it + separator }.toString()
        }
    }

    @TypeConverter
    fun toStringList(elements: String): List<String> {
        return if (elements == "") {
            listOf("")
        } else {
            elements.split(separator).toList()
        }
    }
}