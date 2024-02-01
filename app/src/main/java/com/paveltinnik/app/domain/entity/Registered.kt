package com.paveltinnik.app.domain.entity

import com.google.gson.annotations.SerializedName

data class Registered (
    @SerializedName("date" ) var registeredDate : String? = null,
    @SerializedName("age"  ) var registeredAge  : Int?    = null
)