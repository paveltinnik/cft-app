package com.paveltinnik.app.domain.entity

import com.google.gson.annotations.SerializedName

data class Dob (
    @SerializedName("date" ) var dobDate : String? = null,
    @SerializedName("age"  ) var dobAge  : Int?    = null
)