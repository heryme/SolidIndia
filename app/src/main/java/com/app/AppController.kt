package com.app

import android.app.Application
import com.model.ProductResponse

class AppController: Application()  {

    private var productDataCat: ProductResponse.Data.Category.Product? = null

    override fun onCreate() {
        super.onCreate()

    }

    fun getProductData():ProductResponse.Data.Category.Product{
        return this.productDataCat!!
    }

    fun setProductData(data:ProductResponse.Data.Category.Product) {
        this.productDataCat = data
    }
}