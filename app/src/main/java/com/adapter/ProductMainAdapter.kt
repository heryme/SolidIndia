package com.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.model.ProductResponse
import com.solidindia.R
import kotlinx.android.synthetic.main.row_main_product.view.*

class ProductMainAdapter(var context: Context,
                         var activity:Activity,
                         var productList: ArrayList<ProductResponse.Data>) :
    RecyclerView.Adapter<ProductMainAdapter.SubViewHolder>(), Filterable {

    private lateinit var productMainAdapter: ProductSubAdapter
    private var productFilterdList = ArrayList<ProductResponse.Data>()
    init {
        productFilterdList.addAll(productList)
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
            itemView.tvProductNameMain?.text = productList[position].category.categoryName
            setAdpater(itemView.rvSubProduct,productList[position].category.product as ArrayList<ProductResponse.Data.Category.Product>)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    productFilterdList = productList as ArrayList<ProductResponse.Data>
                } else {
                    val filteredList = ArrayList<ProductResponse.Data>()
                    for (row in productList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.category.categoryName.toLowerCase().contains(charString)) {
                            filteredList.add(row)
                        }
                    }

                    productFilterdList = filteredList
                }

                val filterResults = Filter.FilterResults()
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

    private fun setAdpater(recyclerView: RecyclerView,productSubList: ArrayList<ProductResponse.Data.Category.Product>) {

        productMainAdapter = ProductSubAdapter(context,activity, productSubList)
        recyclerView.adapter = productMainAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}