package com.example.awesomerecipes.helper

import android.content.IntentSender
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import com.example.awesomerecipes.BuildConfig
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn

class SignInHelperImpl(
    private val activity: FragmentActivity
) : SignInHelper, DefaultLifecycleObserver {

    val loginResultHandler = activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
        result -> Log.d(activity::class.simpleName, "${result.resultCode}")
    }

    override fun isUserLogged(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(activity) != null;
    }

    override fun showSignInIntent() {
        val request = GetSignInIntentRequest.builder()
            .setServerClientId(BuildConfig.GOOGLE_OAUTH_CLIENT_ID)
            .build()

        Identity.getSignInClient(activity)
            .getSignInIntent(request)
            .addOnSuccessListener { result ->
                try {
                    ActivityCompat.startIntentSenderForResult(
                        activity,
                        result.intentSender,
                        1,
                        null,
                        0,
                        0,
                        0,
                        null)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("Error", e.toString())
                }
            }.addOnFailureListener { e: Exception ->
                Log.e("Error", e.toString())
            }
    }
}