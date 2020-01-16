package com.frament

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TextView
import com.solidindia.R
import com.solidindia.activity.MainActivity


class WhatsAppFragment : BaseFrament(), View.OnClickListener {

    private var rootView: View? = null
    private lateinit var btnShare: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_whats_app, container, false)
        initIDs(rootView!!)
        initToolbar()
        initListeners()
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu.findItem(R.id.filter)
        item.setVisible(false)
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
        (context as MainActivity).ivbarToolbar.visibility = View.VISIBLE
        (context as MainActivity).llBackMain.visibility = View.GONE
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
