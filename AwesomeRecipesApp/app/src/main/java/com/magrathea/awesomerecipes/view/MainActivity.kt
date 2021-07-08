package com.magrathea.awesomerecipes.view

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.magrathea.awesomerecipes.R
import com.magrathea.awesomerecipes.helper.PermissionManagerImpl
import com.magrathea.awesomerecipes.util.FragmentUtil
import com.magrathea.awesomerecipes.view.recipe.RecipesListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentUtil: FragmentUtil

    @Inject
    lateinit var permissionManager: PermissionManagerImpl

    private var onPermissionGranted: () -> Unit = {
        Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_LONG)
        fragmentUtil.replaceFragmentWithoutHistory(
            fragmentActivity = this,
            fragment = RecipesListFragment.newInstance()
        )
    }
    private var onPermissionDenied: () -> Unit = {
        Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG)
        // Redirect to error fragment.
    }

    private val permissionResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    private fun launchPermissionRequest(
        requestedPermissions: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    )
    {
        this.onPermissionDenied = onPermissionDenied
        this.onPermissionGranted = onPermissionGranted
        permissionResultLauncher.launch(requestedPermissions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionManager.permissionLauncher = this::launchPermissionRequest

        permissionManager.requestPermission(
            requestedPermissions = Manifest.permission.ACCESS_FINE_LOCATION,
            onPermissionGranted = this.onPermissionGranted,
            onPermissionDenied = this.onPermissionDenied
        )
    }

    override fun onResume() {
        super.onResume()
        if (!permissionManager.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionManager.requestPermission(
                requestedPermissions = Manifest.permission.ACCESS_FINE_LOCATION,
                onPermissionGranted = this.onPermissionGranted,
                onPermissionDenied = this.onPermissionDenied
            )
        }
    }
}