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



       fun fetchCharacterDetails(characterId: Int)
         {
            viewModelScope.launch(Dispatchers.IO){
                repository.getCharacterDetails(characterId)
            }
        }

        val characterDetails : LiveData<Response<CharacterDetails>>
        get() = repository.characterDetails
}