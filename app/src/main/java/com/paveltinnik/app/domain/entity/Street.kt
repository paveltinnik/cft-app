package com.paveltinnik.app.domain.entity

import com.google.gson.annotations.SerializedName

data class Street (
    @SerializedName("number" ) var number : Int?    = null,
    @SerializedName("name"   ) var streetName   : String? = null
)