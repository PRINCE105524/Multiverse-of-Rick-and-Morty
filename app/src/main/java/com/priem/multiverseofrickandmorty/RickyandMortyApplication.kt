package com.priem.multiverseofrickandmorty

import android.app.Application
import com.priem.multiverseofrickandmorty.api.CharacterService
import com.priem.multiverseofrickandmorty.api.RetrofitHelper
import com.priem.multiverseofrickandmorty.repository.CharacterListRepository

class RickyandMortyApplication : Application() {

    lateinit var characterListRepository: CharacterListRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {

        val characterService = RetrofitHelper.getInstance().create(CharacterService::class.java)
        characterListRepository = CharacterListRepository(characterService, applicationContext)

    }
}