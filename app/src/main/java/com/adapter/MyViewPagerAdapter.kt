package com.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.app.AppController
import com.bumptech.glide.Glide
import com.github.tntkhang.fullscreenimageview.library.TouchImageView
import com.model.ProductResponse
import com.solid1972.R
import com.solid1972.activity.ShowFullImageActivity
import com.utils.loadImage


class MyViewPagerAdapter(
    private val activity: Activity,
    private val context: Context,
    internal var introSliderList: ArrayList<ProductResponse.Data.Category.Product.ProductImage>,
    var isClickViewPagerImage: Boolean
) : PagerAdapter() {
    private val layoutInflater: LayoutInflater
    lateinit var appcontroller: AppController

    init {
        appcontroller = activity.application as AppController
        layoutInflater = LayoutInflater.from(context)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View
        if (isClickViewPagerImage) {
            view = layoutInflater.inflate(R.layout.row_slider, container, false)
            val (path, type) = introSliderList[position]
            val ivSilder = view.findViewById<View>(R.id.ivSilder) as ImageView

            loadImage(path, appcontroller.getAppContext()!!, ivSilder, R.mipmap.ic_launcher)

            if (isClickViewPagerImage) {
                ivSilder.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        val intent = Intent(context, ShowFullImageActivity::class.java)
                        intent.putExtra("position", position)
                        intent.putParcelableArrayListExtra("sliderList", introSliderList)
                        context.startActivity(intent)
                    }
                })
            }
        } else {
            view = layoutInflater.inflate(R.layout.row_slider_full_image, container, false)
            val ivFullImage = view.findViewById<View>(R.id.iv_content) as TouchImageView
            val (path, type) = introSliderList[position]

            //        TouchImageView ivContent = view.findViewById(R.id.iv_content);

            ivFullImage.setZoom(0.99f);
            Glide.with(appcontroller.getAppContext()!!)
                    .load(path)
                    .into(ivFullImage)

            //  loadImage(path, appcontroller.getAppContext()!!, ivFullImage, R.mipmap.ic_launcher)
        }

        /* Glide.with(context)
                    .load(item.getPath())
                    .error(R.mipmap.ic_launcher)
                    .into(ivSilder);*/


        //view.setBackgroundColor(Color.parseColor(item.getBackgroundColor())/*colors[position]*/);
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return introSliderList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}
