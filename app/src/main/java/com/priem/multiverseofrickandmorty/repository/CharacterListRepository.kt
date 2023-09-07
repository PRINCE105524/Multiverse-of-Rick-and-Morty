package com.priem.multiverseofrickandmorty.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.priem.multiverseofrickandmorty.api.CharacterServiceAPI
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.Exception

class CharacterListRepository @Inject constructor(
    private val characterServiceAPI: CharacterServiceAPI,
    @ApplicationContext private val applicationContext: Context
) {

    private val characterListLiveData = MutableLiveData<Response<CharacterList>>()

    val characterList: LiveData<Response<CharacterList>>
        get() = characterListLiveData

    suspend fun getCharacterList() {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val result = characterServiceAPI.getCharacterList()
                if (result.body() != null) {
                    characterListLiveData.postValue(Response.Success(result.body()))
                } else {
                    characterListLiveData.postValue(Response.Error("API Error"))
                }
            } catch (e: Exception) {
                characterListLiveData.postValue(Response.Error(e.message.toString()))

            }

        } else {
            Log.d("NOINTERNET", "Offline caching will implement later")
            characterListLiveData.postValue(Response.Error("Internet is not available"))
        }


    }


}