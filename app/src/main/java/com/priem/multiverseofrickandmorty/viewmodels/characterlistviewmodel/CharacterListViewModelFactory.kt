package com.priem.multiverseofrickandmorty.viewmodels.characterlistviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.repository.CharacterListRepository

@Suppress("UNCHECKED_CAST")
class CharacterListViewModelFactory (private val repository: CharacterListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            return CharacterListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}