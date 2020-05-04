package com.solid1972.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adapter.MyViewPagerAdapter
import com.app.AppController
import com.model.ProductResponse
import com.solid1972.R
import com.utils.loadImage
import kotlinx.android.synthetic.main.activity_show_full_imae.*


class ShowFullImageActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var appcontroller: AppController
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    var introSliderList: ArrayList<ProductResponse.Data.Category.Product.ProductImage>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_full_imae)
        appcontroller = application as AppController
        setListener()
        val extras = intent.extras
        if (extras != null) {
            val position = extras.getInt("position")
            val sliderImageList =
                extras.getParcelableArrayList<ProductResponse.Data.Category.Product.ProductImage>("sliderList")
            setAdapter(sliderImageList)
            vpSlider.setCurrentItem(position);
            Log.e("ShowFullImageActivity", "Size---->" + sliderImageList.size)

        }

    }

    private fun setAdapter(imageList: ArrayList<ProductResponse.Data.Category.Product.ProductImage>) {
        introSliderList = ArrayList()
        introSliderList!!.addAll(imageList)
        myViewPagerAdapter = MyViewPagerAdapter(
            ShowFullImageActivity@ this,
            ShowFullImageActivity@ this,
            introSliderList!!,
            false
        )
        vpSlider.setAdapter(myViewPagerAdapter);
        //vpSlider.addOnPageChangeListener(viewPagerPageChangeListener);

        /* var hun = getResources().getDimensionPixelSize(R.dimen._20sdp);
         if (introSliderList?.size!! > 1) {
             vpSlider.setClipToPadding(false)
             vpSlider.setPadding(0, 0, hun, 0)
         }else{
             vpSlider.setClipToPadding(true)
             vpSlider.setPadding(0, 0, 0, 0)
         }*/
    }

    override fun onClick(view: View?) {
        when (view) {
            llBack -> {
                finish()
            }
        }

    }

    fun setListener() {
        llBack.setOnClickListener(this)
    }
}
