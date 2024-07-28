package com.target.targetcasestudy.models

import com.google.gson.annotations.SerializedName

data class DealResponse(
    @SerializedName("products") val deals: List<Deal>
)
