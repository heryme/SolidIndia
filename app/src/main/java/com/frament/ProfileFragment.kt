package com.frament

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.solidindia.R
import com.utils.PDF_URL
import com.utils.getLanguageType


class ProfileFragment : BaseFrament(), View.OnClickListener {
    private val TAG :String = javaClass.simpleName
    private lateinit var btnDownload: TextView
    private var rootView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        initIDs(rootView!!)
        initComponent()
        initListeners()
        return rootView
    }

    override fun initComponent() {
        val langTyape = sessionManager["type", "en"]
        Log.e(TAG, "LanType-->$langTyape")

        getLanguageType(activity!!,langTyape)

    }

    override fun onClick(view: View?) {
        when (view) {
            btnDownload -> {

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PDF_URL))
                startActivity(browserIntent)
            }
        }
    }


    override fun initToolbar() {

    }

    override fun initListeners() {
        btnDownload.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun initIDs(rootView: View) {
        btnDownload = rootView.findViewById(R.id.btnDownload)
    }


}
