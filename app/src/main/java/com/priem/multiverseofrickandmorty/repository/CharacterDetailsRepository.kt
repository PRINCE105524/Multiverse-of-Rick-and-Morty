package com.priem.multiverseofrickandmorty.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.priem.multiverseofrickandmorty.api.CharacterService
import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.utils.NetworkUtils

class CharacterDetailsRepository (private val characterService: CharacterService,
                                  private val applicationContext: Context
) {

    private val characterDetailsLiveData = MutableLiveData<CharacterDetails>()

    val characterDetails: LiveData<CharacterDetails>
        get() = characterDetailsLiveData

    suspend fun getCharacterDetails(characterId: Int){
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = characterService.getCharacterById(characterId)
            if(result.body() != null){
                characterDetailsLiveData.postValue(result.body())
            }
        }
        else
        {
            Log.d("NO_INTERNET","Offline caching will implement later")
        }


    }

}