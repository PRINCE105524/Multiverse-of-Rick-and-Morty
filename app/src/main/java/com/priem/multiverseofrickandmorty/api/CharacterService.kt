package com.priem.multiverseofrickandmorty.api

import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {
    //List of Character
    @GET("character")
    suspend fun getCharacterList() : Response<CharacterList>

}