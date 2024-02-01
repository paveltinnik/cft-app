package com.paveltinnik.app.domain.entity

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Location (
    @Embedded
    @SerializedName("street"      ) var street      : Street?      = Street(),
    @SerializedName("city"        ) var city        : String?      = null,
    @SerializedName("state"       ) var state       : String?      = null,
    @SerializedName("country"     ) var country     : String?      = null,
    @SerializedName("postcode"    ) var postcode    : String?      = null,
    @Embedded
    @SerializedName("coordinates" ) var coordinates : Coordinates? = Coordinates(),
    @Embedded
    @SerializedName("timezone"    ) var timezone    : Timezone?    = Timezone()
)