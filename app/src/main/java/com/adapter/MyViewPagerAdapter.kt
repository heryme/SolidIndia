package com.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.Image
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.viewpager.widget.PagerAdapter
import com.app.AppController

import com.bumptech.glide.Glide
import com.model.ProductResponse
import com.solidindia.R
import com.utils.loadImage
import kotlinx.android.synthetic.main.row_sub_product.view.*

class MyViewPagerAdapter(
    private val activity:Activity,
    private val context: Context,
    internal var introSliderList: List<ProductResponse.Data.Category.Product.ProductImage>
) : PagerAdapter() {
    private val layoutInflater: LayoutInflater
    lateinit var appcontroller: AppController
    init {
        appcontroller = activity.application as AppController
        layoutInflater = LayoutInflater.from(context)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.row_slider, container, false)
        val (path, type) = introSliderList[position]
        val ivSilder = view.findViewById<View>(R.id.ivSilder) as ImageView

        loadImage(path,
            appcontroller.getAppContext()!!, ivSilder,
            R.mipmap.ic_launcher)

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
