package com.model


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class Data(
        @SerializedName("category")
        val category: Category
    ) {
        data class Category(
            @SerializedName("category_arabian")
            val categoryArabian: String,
            @SerializedName("category_chinese")
            val categoryChinese: String,
            @SerializedName("category_english")
            val categoryEnglish: String,
            @SerializedName("category_french")
            val categoryFrench: String,
            @SerializedName("categoryName")
            val categoryName: String,
            @SerializedName("category_spanish")
            val categorySpanish: String,
            @SerializedName("category_tamil")
            val categoryTamil: String,
            @SerializedName("category_telugu")
            val categoryTelugu: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_delete")
            val isDelete: String,
            @SerializedName("product")
            val product: List<Product>,
            @SerializedName("updated_at")
            val updatedAt: String
        ) {
            data class Product(
                @SerializedName("capacity")
                val capacity: String,
                @SerializedName("drum_diameter")
                val drumDiameter: String,
                @SerializedName("highlights")
                val highlights: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("price")
                val price: String,
                @SerializedName("product_image")
                val productImage: List<ProductImage>,
                @SerializedName("productName")
                val productName: String,
                @SerializedName("storage")
                val storage: String
            ) {
                data class ProductImage(
                    @SerializedName("path")
                    val path: String,
                    @SerializedName("type")
                    val type: String
                )
            }
        }
    }
}