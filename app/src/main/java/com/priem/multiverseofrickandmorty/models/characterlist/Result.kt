package com.priem.multiverseofrickandmorty.models.characterlist

import android.os.Parcel
import android.os.Parcelable


data class Result(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)