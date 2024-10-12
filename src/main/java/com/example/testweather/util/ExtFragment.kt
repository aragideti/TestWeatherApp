package com.example.testweather.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment

fun Fragment.fullScreenDialogwithBackground(root: View): Dialog {
    return Dialog(root.context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action === KeyEvent.ACTION_UP) {
                this.dismiss()
                true
            } else false
        }
    }
}

