package com.example.awesomerecipes.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionHelperImpl(
    private val activity: Activity,
    private val permissionLauncher: (requestedPermissions: String, onPermissionGranted: () -> Unit, onPermissionDenied: () -> Unit) -> Unit
) : PermissionHelper {

    private fun checkPermissionsStatus(requestedPermission: String): Int {
        return ContextCompat.checkSelfPermission(activity.applicationContext, requestedPermission)
    }

    override fun requestPermission(
        requestedPermission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        permissionLauncher(requestedPermission, onPermissionGranted, onPermissionDenied)
    }

    override fun isPermissionGranted(requestedPermission: String): Boolean {
        return checkPermissionsStatus(requestedPermission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionRationale(
        rationaleTextId: Int,
        requestedPermission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        activity.runOnUiThread {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(activity.getString(rationaleTextId))
            builder.setPositiveButton("Ok") { _, _ ->
                requestPermission(requestedPermission, onPermissionGranted, onPermissionDenied)
            }
            builder.show()
        }
    }

    override fun handlePermission(
        requestedPermission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit,
        rationaleTextId: Int
    ) {
        when {
            checkPermissionsStatus(requestedPermission) == PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }
            activity.shouldShowRequestPermissionRationale(requestedPermission) -> {
                requestPermissionRationale(rationaleTextId, requestedPermission, onPermissionGranted, onPermissionDenied)
            }
            else -> {
                requestPermission(requestedPermission, onPermissionGranted, onPermissionDenied)
            }
        }
    }

}