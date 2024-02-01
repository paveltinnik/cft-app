package com.paveltinnik.app.domain.entity

import com.google.gson.annotations.SerializedName

data class PersonsResponse (
    @SerializedName("results" ) var results : MutableList<Person> = arrayListOf(),
    @SerializedName("info"    ) var info    : Info?              = Info()
)