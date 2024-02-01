package com.paveltinnik.app.domain.entity

import com.google.gson.annotations.SerializedName

data class Timezone (
    @SerializedName("offset"      ) var offset      : String? = null,
    @SerializedName("description" ) var description : String? = null
)