package com.example.awesomerecipes.helper

import com.example.awesomerecipes.R

interface PermissionHelper {
    fun requestPermission(
        requestedPermission: String,
        onPermissionGranted: () -> Unit = {},
        onPermissionDenied: () -> Unit = {}
    )

    fun isPermissionGranted(requestedPermission: String): Boolean

    fun handlePermission(
        requestedPermission: String,
        onPermissionGranted: () -> Unit = {},
        onPermissionDenied: () -> Unit = {},
        rationaleTextId: Int = R.string.permission_rationale_default_text
    )
}