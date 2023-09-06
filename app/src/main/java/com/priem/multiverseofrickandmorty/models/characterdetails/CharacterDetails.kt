package com.priem.multiverseofrickandmorty.model.characterdetails

import com.priem.multiverseofrickandmorty.models.characterdetails.Location
import com.priem.multiverseofrickandmorty.models.characterdetails.Origin

data class CharacterDetails(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)