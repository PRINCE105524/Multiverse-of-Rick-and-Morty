package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.repository.CharacterDetailsRepository
import com.priem.multiverseofrickandmorty.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailsViewModel (private val repository: CharacterDetailsRepository) : ViewModel()  {

        init {
            viewModelScope.launch(Dispatchers.IO){
                repository.getCharacterDetails(1)
            }
        }

        val characterDetails : LiveData<Response<CharacterDetails>>
        get() = repository.characterDetails
}