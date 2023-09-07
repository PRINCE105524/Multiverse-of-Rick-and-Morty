package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.repository.CharacterDetailsRepository
import com.priem.multiverseofrickandmorty.repository.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: CharacterDetailsRepository) :
    ViewModel() {


    val characterDetails: LiveData<Response<CharacterDetails>>
        get() = repository.characterDetails

    fun fetchCharacterDetails(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacterDetails(characterId)
        }
    }


}