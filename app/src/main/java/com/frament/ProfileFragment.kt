package com.frament

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.solidindia.R
import com.solidindia.activity.MainActivity
import com.utils.FB_URL
import com.utils.PDF_URL
import com.utils.YOUTUE_URL
import com.utils.getLanguageType


class ProfileFragment : BaseFrament(), View.OnClickListener {
    private val TAG: String = javaClass.simpleName
    private lateinit var ivPdf: ImageView
    private lateinit var ivFacebook: ImageView
    private lateinit var ivInsta: ImageView
    private lateinit var ivYoutube: ImageView
    private var rootView: View? = null

    /*  private lateinit var ivFacebook: ImageView
      private lateinit var ivYoutube: ImageView
  */
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
        initToolbar()
        initListeners()
        return rootView
    }

    override fun initComponent() {
        val langTyape = sessionManager["type", "en"]
        Log.e(TAG, "LanType-->$langTyape")

        getLanguageType(activity!!, langTyape)

    }

    override fun onClick(view: View?) {
        when (view) {
            ivPdf -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PDF_URL))
                startActivity(browserIntent)
            }
            ivFacebook -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(FB_URL))
                startActivity(browserIntent)
            }
            ivYoutube -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUE_URL))
                startActivity(browserIntent)
            }
            ivInsta -> {

            }
        }
    }


    override fun initToolbar() {
        (context as MainActivity).ivbarToolbar.visibility = View.VISIBLE
        (context as MainActivity).llBackMain.visibility = View.GONE
    }

    override fun initListeners() {
        ivPdf.setOnClickListener(this)
        ivFacebook.setOnClickListener(this)
        ivInsta.setOnClickListener(this)
        ivYoutube.setOnClickListener(this)

        /* ivYoutube.setOnClickListener(this)
         ivFacebook.setOnClickListener(this)*/
    }

    override fun initData() {

    }

    override fun initIDs(rootView: View) {
        ivPdf = rootView.findViewById(R.id.ivPdf)
        ivFacebook = rootView.findViewById(R.id.ivFacebook)
        ivInsta = rootView.findViewById(R.id.ivInsta)
        ivYoutube = rootView.findViewById(R.id.ivYoutube)
        /*  ivFacebook = rootView.findViewById(R.id.ivFacebook)
          ivYoutube = rootView.findViewById(R.id.ivYoutube)*/

    }


}
