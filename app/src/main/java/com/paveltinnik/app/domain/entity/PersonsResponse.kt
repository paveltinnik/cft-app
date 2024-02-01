package com.paveltinnik.app.domain.entity

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PersonsResponse (

    @SerializedName("results" ) var results : ArrayList<Person> = arrayListOf(),
    @SerializedName("info"    ) var info    : Info?              = Info()

)



data class Name (

    @SerializedName("title" ) var title : String? = null,
    @SerializedName("first" ) var first : String? = null,
    @SerializedName("last"  ) var last  : String? = null

)

data class Street (

    @SerializedName("number" ) var number : Int?    = null,
    @SerializedName("name"   ) var streetName   : String? = null

)

data class Coordinates (

    @SerializedName("latitude"  ) var latitude  : String? = null,
    @SerializedName("longitude" ) var longitude : String? = null

)

data class Timezone (

    @SerializedName("offset"      ) var offset      : String? = null,
    @SerializedName("description" ) var description : String? = null

)

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

data class Login (

    @SerializedName("uuid"     ) var uuid     : String? = null,
    @SerializedName("username" ) var username : String? = null,
    @SerializedName("password" ) var password : String? = null,
    @SerializedName("salt"     ) var salt     : String? = null,
    @SerializedName("md5"      ) var md5      : String? = null,
    @SerializedName("sha1"     ) var sha1     : String? = null,
    @SerializedName("sha256"   ) var sha256   : String? = null

)

data class Dob (

    @SerializedName("date" ) var dobDate : String? = null,
    @SerializedName("age"  ) var dobAge  : Int?    = null

)

data class Registered (

    @SerializedName("date" ) var registeredDate : String? = null,
    @SerializedName("age"  ) var registeredAge  : Int?    = null

)

data class Id (

    @NonNull
    @SerializedName("name"  ) var idName  : String = "",
    @NonNull
    @SerializedName("value" ) var value : String = ""

)

data class Picture (

    @SerializedName("large"     ) var large     : String? = null,
    @SerializedName("medium"    ) var medium    : String? = null,
    @SerializedName("thumbnail" ) var thumbnail : String? = null

)

data class Info (

    @SerializedName("seed"    ) var seed    : String? = null,
    @SerializedName("results" ) var results : Int?    = null,
    @SerializedName("page"    ) var page    : Int?    = null,
    @SerializedName("version" ) var version : String? = null

)