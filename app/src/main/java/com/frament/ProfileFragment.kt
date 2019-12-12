package com.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solidindia.R


class ProfileFragment : BaseFrament(), View.OnClickListener {

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
        initListeners()
        return rootView
    }

    override fun initComponent() {

    }

    override fun onClick(view: View?) {
        when (view) {

            }
    }


    override fun initToolbar() {

    }

    override fun initListeners() {

    }

    override fun initData() {

    }

    override fun initIDs(rootView: View) {

    }


}
