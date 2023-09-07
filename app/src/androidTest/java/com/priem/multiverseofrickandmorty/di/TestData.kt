package com.priem.multiverseofrickandmorty.di

import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.models.characterlist.Info
import com.priem.multiverseofrickandmorty.models.characterlist.Result

object TestData {
    val characterList = CharacterList(
        info = Info(count = 3, pages = 1, next = "2", prev = null),
        results = listOf(
            Result(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            ),
            Result(
                id = 2,
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
            ),
            Result(
                id = 3,
                name = "Summer Smith",
                status = "Alive",
                species = "Human",
                gender = "Female",
                image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg"
            )
        )
    )

}
