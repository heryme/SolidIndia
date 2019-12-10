package com.frament

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.solidindia.R


class WhatsAppFragment : BaseFrament(), View.OnClickListener {

    private var rootView: View? = null
    private lateinit var btnShare: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_whats_app, container, false)
        initIDs(rootView!!)
        initListeners()
        return rootView
    }

    override fun initComponent() {

    }

    override fun onClick(view: View?) {
        when (view) {
            btnShare -> {
                val phoneNumberWithCountryCode = "+919624777773"
                val message = "Solid India"

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            String.format(
                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumberWithCountryCode, message
                            )
                        )
                    )
                )
            }
        }
    }


    override fun initToolbar() {

    }

    override fun initListeners() {
        btnShare.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun initIDs(rootView: View) {
        btnShare = rootView.findViewById(R.id.btnShare)
    }


}
