package com.priem.multiverseofrickandmorty.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.priem.multiverseofrickandmorty.api.CharacterServiceAPI
import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CharacterDetailsRepository @Inject constructor(
    private val characterServiceAPI: CharacterServiceAPI,
    @ApplicationContext private val applicationContext: Context
) {

    private val characterDetailsLiveData = MutableLiveData<Response<CharacterDetails>>()

    val characterDetails: LiveData<Response<CharacterDetails>>
        get() = characterDetailsLiveData

    suspend fun getCharacterDetails(characterId: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val result = characterServiceAPI.getCharacterById(characterId)
                if (result.body() != null) {
                    characterDetailsLiveData.postValue(Response.Success(result.body()))
                } else {
                    characterDetailsLiveData.postValue(Response.Error("API Error"))
                }
            } catch (e: Exception) {
                characterDetailsLiveData.postValue(Response.Error(e.message.toString()))

            }

        } else {
            Log.d("NOINTERNET", "Offline caching will implement later")
            characterDetailsLiveData.postValue(Response.Error("Internet is not available"))
        }

    }

}