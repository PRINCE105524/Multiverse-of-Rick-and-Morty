package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.repository.CharacterListRepository

class CharacterListViewModelFactory (private val repository: CharacterListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterListViewModel(repository) as T
    }
}