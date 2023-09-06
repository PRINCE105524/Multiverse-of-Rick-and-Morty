package com.priem.multiverseofrickandmorty

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.repository.Response
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModel
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModelFactory
import com.priem.multiverseofrickandmorty.viewmodels.CharacterListViewModel
import com.priem.multiverseofrickandmorty.viewmodels.CharacterListViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    lateinit var characterListViewModel: CharacterListViewModel
    lateinit var characterDetailsViewModel: CharacterDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)

        //characterList
        val repositoryCharacterList = (application as RickyandMortyApplication).characterListRepository
        characterListViewModel = ViewModelProvider(this, CharacterListViewModelFactory(repositoryCharacterList)).get(CharacterListViewModel::class.java)
        characterListViewModel.characterList.observe(this, Observer {
            when(it)
            {
                is Response.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    it.data?.let {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_LONG).show()
                        Log.d("characterList", it.results.toString()) }

                }
                is Response.Error -> {
                    it.errorMessage
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
                }
            }
        })

        //characterDetails
        val repositoryCharacterDetails = (application as RickyandMortyApplication).characterDetailsRepository
        characterDetailsViewModel = ViewModelProvider(this, CharacterDetailsViewModelFactory(repositoryCharacterDetails)).get(CharacterDetailsViewModel::class.java)
        characterDetailsViewModel.characterDetails.observe(this, Observer{
            when(it)
            {
                is Response.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    it.data?.let {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_LONG).show()
                        Log.d("characterList", it.toString()) }

                }
                is Response.Error -> {
                    it.errorMessage
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
                }
            }
        })


    }
}
