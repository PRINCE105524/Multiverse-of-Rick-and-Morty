package com.priem.multiverseofrickandmorty.api

import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    //List of Character
    @GET("character")
    suspend fun getCharacterList() : Response<CharacterList>

    //Individual Character Details
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int): Response<CharacterDetails>




}