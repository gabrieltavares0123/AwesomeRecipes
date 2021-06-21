package com.example.awesomerecipes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.awesomerecipes.ui.theme.AwesomeRecipesTheme
import com.example.awesomerecipes.welcome.WelcomeLayout
import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class MainActivity : ComponentActivity() {

    private var onPermissionGranted: () -> Unit = {}
    private var onPermissionDenied: () -> Unit = {}

    private val permissionResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
    }

    private fun launchPermissionRequest(
        requestedPermission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
       this.onPermissionGranted = onPermissionGranted
       this.onPermissionDenied = onPermissionDenied
       permissionResultLauncher.launch(requestedPermission)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AwesomeRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    WelcomeLayout().Greeting("Android")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("Error", data.toString())
        val completedTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        completedTask.getResult(ApiException::class.java)
    }
}