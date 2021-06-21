package com.example.awesomerecipes.welcome

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.awesomerecipes.ui.theme.AwesomeRecipesTheme

class WelcomeLayout {



    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AwesomeRecipesTheme {
            Greeting("Android")
        }
    }
}