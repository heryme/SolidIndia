package com.solid1972.activity

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.app.AppController
import com.google.android.material.snackbar.Snackbar
import com.utils.SessionManager

abstract class BaseActivity : AppCompatActivity() {

    lateinit var sessionManager: SessionManager
    lateinit var snackbar: Snackbar
    protected lateinit var mActivity: Activity
    protected lateinit var appcontroller: AppController
    protected lateinit var mContext: Context

}
