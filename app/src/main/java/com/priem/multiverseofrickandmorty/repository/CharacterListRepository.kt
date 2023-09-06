package com.priem.multiverseofrickandmorty.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.priem.multiverseofrickandmorty.api.CharacterService
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.utils.NetworkUtils

class CharacterListRepository(
    private val characterService: CharacterService,
    private val applicationContext: Context
    ) {

    private val characterListLiveData = MutableLiveData<CharacterList>()

    val characterList: LiveData<CharacterList>
        get() = characterListLiveData

    suspend fun getCharacterList(){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = characterService.getCharacterList()
            if(result.body() != null){
                characterListLiveData.postValue(result.body())
            }
        }
        else
        {
            Log.d("NOINTERNET","Offline caching will implement later")
        }



    }




}