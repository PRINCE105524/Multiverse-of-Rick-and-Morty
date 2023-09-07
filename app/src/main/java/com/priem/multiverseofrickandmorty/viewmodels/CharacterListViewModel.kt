package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.repository.CharacterListRepository
import com.priem.multiverseofrickandmorty.repository.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: CharacterListRepository) :
    ViewModel() {

    val characterList: LiveData<Response<CharacterList>>
        get() = repository.characterList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacterList()
        }
    }

}