package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.repository.CharacterDetailsRepository

@Suppress("UNCHECKED_CAST")
class CharacterDetailsViewModelFactory (private val repository: CharacterDetailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}