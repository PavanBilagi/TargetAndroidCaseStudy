package com.target.targetcasestudy.models

import com.google.gson.annotations.SerializedName

data class Deal(
    val id: String?,
    val title: String?,
    val aisle: String?,
    val description: String?,
    @SerializedName("sale_price") val salePrice: Price?,
    @SerializedName("regular_price") val regularPrice: Price?,
    @SerializedName("image_url") val imageUrl: String?,
    val fulfillment: String?,
    val availability: String?,
)
