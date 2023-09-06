package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.repository.CharacterListRepository
import com.priem.multiverseofrickandmorty.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel (private val repository: CharacterListRepository) : ViewModel()  {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getCharacterList()
        }
    }

    val characterList : LiveData<Response<CharacterList>>
        get() = repository.characterList
}