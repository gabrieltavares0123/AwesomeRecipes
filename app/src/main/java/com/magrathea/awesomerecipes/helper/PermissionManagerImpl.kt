package com.magrathea.awesomerecipes.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.magrathea.awesomerecipes.R
import javax.inject.Inject

class PermissionManagerImpl @Inject constructor(
    private val activity: Activity
) : PermissionManager {

    lateinit var permissionLauncher: (
        requestedPermissions: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) -> Unit


    private fun checkPermissionStatus(requestedPermission: String): Int {
        return ContextCompat.checkSelfPermission(activity.applicationContext, requestedPermission)
    }

    override fun requestPermission(
        requestedPermissions: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        permissionLauncher(requestedPermissions, onPermissionGranted, onPermissionDenied)
    }

    override fun isPermissionGranted(requestedPermission: String): Boolean {
        return checkPermissionStatus(requestedPermission) == PackageManager.PERMISSION_GRANTED
    }

    override fun handlePermission(
        requestedPermission: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit,
        rationaleTextId: Int
    ) {
        when {
            checkPermissionStatus(requestedPermission) == PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }
            activity.shouldShowRequestPermissionRationale(requestedPermission) -> {
                requestPermissionRationale(
                    rationaleTextId,
                    requestedPermission,
                    onPermissionGranted,
                    onPermissionDenied
                )
            }
            else -> {
                requestPermission(requestedPermission, onPermissionGranted, onPermissionDenied)
            }
        }
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
            builder.setPositiveButton(activity.getString(R.string.confirm)) { _, _ ->
                requestPermission(requestedPermission, onPermissionGranted, onPermissionDenied)
            }
            builder.show()
        }
    }
}