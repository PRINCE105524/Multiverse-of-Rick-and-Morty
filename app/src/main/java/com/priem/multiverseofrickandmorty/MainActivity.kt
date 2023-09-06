package com.priem.multiverseofrickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModel
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModelFactory
import com.priem.multiverseofrickandmorty.viewmodels.CharacterListViewModel
import com.priem.multiverseofrickandmorty.viewmodels.CharacterListViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var characterListViewModel: CharacterListViewModel
    lateinit var characterDetailsViewModel: CharacterDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //characterList
        val repositoryCharacterList = (application as RickyandMortyApplication).characterListRepository
        characterListViewModel = ViewModelProvider(this, CharacterListViewModelFactory(repositoryCharacterList)).get(CharacterListViewModel::class.java)
        characterListViewModel.characterList.observe(this, Observer {
            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_LONG).show()
            Log.d("characterList", it.results.toString())
        })

        //characterDetails
        val repositoryCharacterDetails = (application as RickyandMortyApplication).characterDetailsRepository
        characterDetailsViewModel = ViewModelProvider(this, CharacterDetailsViewModelFactory(repositoryCharacterDetails)).get(CharacterDetailsViewModel::class.java)
        characterDetailsViewModel.characterDetails.observe(this, Observer{
            Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_LONG).show()
            Log.d("characterDetails", it.toString())
        })


    }
}
