package com.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.AppController
import com.frament.HomeFragment
import com.model.ProductResponse
import com.solidindia.R
import kotlinx.android.synthetic.main.row_main_product.view.*



class ProductMainAdapter(var context: Context,
                         var activity:Activity,
                         var productList: ArrayList<ProductResponse.Data>) :
    RecyclerView.Adapter<ProductMainAdapter.SubViewHolder>(), Filterable {

    private lateinit var productMainAdapter: ProductSubAdapter
    var appcontroller: AppController
    private var productFilterdList = ArrayList<ProductResponse.Data>()
    init {
        productFilterdList.addAll(productList)
        appcontroller = activity.application as AppController
    }
    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        holder.bind(position, productList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder =
        SubViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_main_product,
                parent,
                false
            )
        )

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = productFilterdList.size

    inner class SubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, productList: List<ProductResponse.Data>) {
            itemView.tvProductNameMain?.text = productFilterdList[position].category.categoryName
            Log.e("TAG","Temp List Size--->${HomeFragment.tempList.size}")
            setAdpater(itemView.rvSubProduct,productFilterdList[position].category.product as ArrayList<ProductResponse.Data.Category.Product>,position)
            appcontroller.setMainAdapterPost(position)

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    productFilterdList = productList
                } else {
                    val filteredList = ArrayList<ProductResponse.Data>()
                    for (row in productList) {

                        if (row.category.categoryName.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    productFilterdList = filteredList
                }


                var filterResults = Filter.FilterResults()
                filterResults.values = productFilterdList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {
                productFilterdList = filterResults.values as ArrayList<ProductResponse.Data>
                notifyDataSetChanged()
            }
        }
    }

    private fun setAdpater(recyclerView: RecyclerView,productSubList: ArrayList<ProductResponse.Data.Category.Product>,position: Int) {
        productMainAdapter = ProductSubAdapter(context,activity, productSubList,position,true)
        recyclerView.adapter = productMainAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}