package com.andrejskijonoks.job_interview_template.models

import android.os.Parcelable
import android.text.Spanned
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("short_name")
    val name: String,
    @SerializedName("category_id")
    val id: String,
    val url: String
) : Parcelable

data class ProductHolder(
    val total: Int,
    val gridProducts: Map<String, List<Product>>
)

@Parcelize
data class Product(
    @SerializedName("fullName")
    val name: String,
    @SerializedName("primaryImage")
    var imagePath: String,
    val url: String,
    val price: Double,
    var presentationPrice: String,
) : Parcelable

data class DetailedProduct(
    @SerializedName("primary_image")
    var detailsImagePath: String,
    var presentationPrice: String,
    @SerializedName("min_sale_price")
    val minSalePrice: Double,
    @SerializedName("short_name")
    val shortName: String,
    val description: String,
    var presentationDescription: Spanned
)