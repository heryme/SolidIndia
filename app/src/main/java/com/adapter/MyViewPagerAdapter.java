package com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.model.ProductResponse;
import com.solidindia.R;

import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    List<ProductResponse.Data.Category.Product.ProductImage> introSliderList;
    public MyViewPagerAdapter(Context context,
                              List<ProductResponse.Data.Category.Product.ProductImage> introSliderList) {
        layoutInflater = LayoutInflater.from(context);
        this.introSliderList = introSliderList;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.row_slider, container, false);
        ProductResponse.Data.Category.Product.ProductImage item = introSliderList.get(position);
        ImageView ivSilder = (ImageView) view.findViewById(R.id.ivSilder);

        Glide.with(context)
                    .load(item.getPath())
                    .error(R.mipmap.ic_launcher)
                    .into(ivSilder);


        //view.setBackgroundColor(Color.parseColor(item.getBackgroundColor())/*colors[position]*/);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return introSliderList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
