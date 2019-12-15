package com.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.AppController
import com.bumptech.glide.Glide
import com.frament.ProductDetailsFragment
import com.model.ProductResponse
import com.solidindia.R
import com.solidindia.activity.MainActivity
import kotlinx.android.synthetic.main.row_main_product.view.*
import kotlinx.android.synthetic.main.row_sub_product.view.*



class ProductSubAdapter(
    var context: Context,
    var activity: Activity,
    var productSubList: ArrayList<ProductResponse.Data.Category.Product>,
    var pos: Int,
    var isShowAllListItem: Boolean
) :

    RecyclerView.Adapter<ProductSubAdapter.SubViewHolder>() {
    lateinit var appcontroller: AppController
    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        holder.bind(position, productSubList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder =
        SubViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_sub_product,
                parent,
                false
            )
        )

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = productSubList.size

    inner class SubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, productList: ArrayList<ProductResponse.Data.Category.Product>) {
            appcontroller = activity.application as AppController

            if (isShowAllListItem) {
                itemView.visibility = View.VISIBLE
                val params = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                params.setMargins(35, 0, 35, 0);

                itemView.setLayoutParams(params)
            } else {
                if (position == appcontroller.getSubAdapterPost()) {
                    itemView.visibility = View.GONE
                    itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))

                }
            }

            itemView.tvProductName?.text = productList[position].productName
            itemView.tvProductSubName?.text = productList[position].capacity
            if (productList[position].productImage.size > 0) {
                Glide.with(context)
                    .load(productList[position].productImage[0].path)
                    .error(R.mipmap.ic_launcher)
                    .into(itemView.ivSubImage)
            }


            itemView.llSubProduct?.setOnClickListener {
                appcontroller.setProductData(productList[position])
                appcontroller.setSubAdapterPost(position)
                appcontroller.setMainAdapterPost(pos)
                (context as MainActivity).loadFragment(ProductDetailsFragment(), false)
            }
        }
    }
}