package com.solid1972.activity

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.AppController
import com.google.android.material.snackbar.Snackbar
import com.solid1972.R
import com.utils.SessionManager

abstract class BaseActivity : AppCompatActivity() {

    lateinit var sessionManager: SessionManager
    lateinit var snackbar: Snackbar
    protected lateinit var mActivity: Activity
    protected lateinit var appcontroller: AppController
    protected lateinit var mContext: Context

    fun showSnackBar(message: String) {
        val parentLayout = findViewById<View>(android.R.id.content)
        snackbar = Snackbar
            .make(parentLayout, message, Snackbar.LENGTH_SHORT)
            .setAction(getString(R.string.dismiss)) {
                snackbar.dismiss()
            }
        snackbar.setActionTextColor(Color.RED)
        snackbar.duration = 4000
        snackbar.show()
    }

}
