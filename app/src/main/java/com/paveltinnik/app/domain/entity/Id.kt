package com.paveltinnik.app.domain.entity

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Id (
    @NonNull
    @SerializedName("name"  ) var idName : String = "",
    @NonNull
    @SerializedName("value" ) var value : String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Id

        if (idName != other.idName) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idName?.hashCode() ?: 0
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }
}