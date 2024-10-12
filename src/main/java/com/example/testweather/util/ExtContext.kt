package com.example.testweather.util

import android.app.AlertDialog
import android.content.Context
import com.example.testweather.R

fun Context.loadImage(name: String): Int? {
    return this.resources?.getIdentifier(
        name,
        "drawable",
        this.packageName
    )
}

fun Context.showErrorDialog(error: String) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.error))
        .setMessage(error)
        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)
        .show()
}

