package com.app

import android.app.Application
import com.model.ProductResponse

class AppController: Application()  {

    private var productDataCat: ProductResponse.Data.Category.Product? = null
    private var listData:ArrayList<ProductResponse.Data>?= null
    private var mainAdapterSelectionPosition:Int = 0
    private var subbAdapterSelectionPosition:Int = 0

    companion object {
        @get:Synchronized
        var instance: AppController? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getAppContext(): AppController? {
        return instance
    }

    fun getProductData():ProductResponse.Data.Category.Product{
        return this.productDataCat!!
    }

    fun setProductData(data:ProductResponse.Data.Category.Product) {
        this.productDataCat = data
    }

    fun setDataList(list:ArrayList<ProductResponse.Data>){
         this.listData = list
    }

    fun getDataList():ArrayList<ProductResponse.Data> {
        return this.listData!!
    }

    fun setMainAdapterPost(pos:Int){
        this.mainAdapterSelectionPosition = pos
    }

    fun getMainAdapterPost():Int{
        return this.mainAdapterSelectionPosition
    }


    fun setSubAdapterPost(pos:Int){
        this.subbAdapterSelectionPosition = pos
    }

    fun getSubAdapterPost():Int{
        return this.subbAdapterSelectionPosition
    }
}



