package com.magrathea.awesomerecipes.view.recipe

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Class to represent the UI State.
 */
sealed class RecipesListState {

    lateinit var recipesListViewModel: RecipesListViewModel

    @Composable
    abstract fun BuildUI()

    object Nothing : RecipesListState() {

        @Composable
        override fun BuildUI() {
            val coroutineScope = rememberCoroutineScope()

            Column {
                Spacer(modifier = Modifier.height(60.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        recipesListViewModel.uiIntent.emit(RecipesListIntent.FetchRecipes)
                    }
                    Log.d("RecipesListState", "BuildUI: building ui ...")
                },
                    content = { Text(text = "Change state.") }
                )
            }
        }
    }

    object Loading : RecipesListState() {

        @Composable
        override fun BuildUI() {
            Text(text = "Loading something that i don't know...")
        }
    }

    class Success(val data: List<Any>?) : RecipesListState() {

        @Composable
        override fun BuildUI() {
            data?.let {
                Text(text = it.toString())
            }
        }
    }

    class Empty(val message: String) : RecipesListState() {

        @Composable
        override fun BuildUI() {
            Text(text = message)
        }
    }

    class Error(val throwable: Throwable) : RecipesListState() {

        @Composable
        override fun BuildUI() {
            Text(text = throwable.localizedMessage.toString())
        }
    }
}