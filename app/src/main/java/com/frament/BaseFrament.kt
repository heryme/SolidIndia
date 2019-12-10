package com.frament

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.AppController
import com.google.android.material.snackbar.Snackbar
import com.solidindia.R
import com.utils.SessionManager

abstract class BaseFrament : Fragment() {

    protected lateinit var sessionManager: SessionManager
    lateinit var snackbar: Snackbar
    protected lateinit var appcontroller: AppController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(activity!!)
        appcontroller = activity!!.application as AppController
    }


    // where we initialize componants of fragment
    abstract fun initComponent()

    // where we initialize toolbar of fragment
    abstract fun initToolbar()

    // where we initialize listeners of fragment
    abstract fun initListeners()

    // where we set data of fragment
    abstract fun initData()

    // where we initialize id of fragment
    abstract fun initIDs(rootView: View)

    fun showSnackBar(message: String) {
        val parentLayout = activity!!.findViewById<View>(android.R.id.content)
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