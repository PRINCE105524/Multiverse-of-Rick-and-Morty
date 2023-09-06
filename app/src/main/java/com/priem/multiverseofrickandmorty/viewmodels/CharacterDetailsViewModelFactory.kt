package com.priem.multiverseofrickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.repository.CharacterDetailsRepository

class CharacterDetailsViewModelFactory (private val repository: CharacterDetailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(repository) as T
    }
}