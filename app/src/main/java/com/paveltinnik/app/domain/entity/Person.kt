package com.paveltinnik.app.domain.entity

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "persons"
)
data class Person (
    @SerializedName("gender"     ) var gender     : String?     = null,
    @SerializedName("name"       ) var name       : Name?       = Name(),
    @Embedded
    @SerializedName("location"   ) var location   : Location?   = Location(),
    @SerializedName("email"      ) var email      : String?     = null,
    @Embedded
    @SerializedName("login"      ) var login      : Login?      = Login(),
    @Embedded
    @SerializedName("dob"        ) var dob        : Dob?        = Dob(),
    @Embedded
    @SerializedName("registered" ) var registered : Registered? = Registered(),
    @SerializedName("phone"      ) var phone      : String?     = null,
    @SerializedName("cell"       ) var cell       : String?     = null,
    @PrimaryKey
    @NonNull
    @Embedded
    @SerializedName("id"         ) var id         : Id         = Id(),
    @Embedded
    @SerializedName("picture"    ) var picture    : Picture?    = Picture(),
    @SerializedName("nat"        ) var nat        : String?     = null
): Serializable