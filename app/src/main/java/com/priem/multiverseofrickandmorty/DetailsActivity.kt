package com.priem.multiverseofrickandmorty

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.priem.multiverseofrickandmorty.databinding.ActivityDetailsBinding
import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.repository.Response
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModel
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModelFactory

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the characterId from previous intent
        val characterId = intent.getIntExtra("characterId", -1) // -1 is the default value if the key is not found
        Log.d("characterID", characterId.toString())
        getCharacterDetails(characterId)
    }

    ////characterDetails
    private fun getCharacterDetails(characterId : Int) {
        val repositoryCharacterDetails = (application as RickyandMortyApplication).characterDetailsRepository
        characterDetailsViewModel = ViewModelProvider(this, CharacterDetailsViewModelFactory(repositoryCharacterDetails)).get(CharacterDetailsViewModel::class.java)
        characterDetailsViewModel.fetchCharacterDetails(characterId)
        characterDetailsViewModel.characterDetails.observe(this, Observer{
            initCharacterDetails(it)
        })
    }

    //initialize character details view
    private fun initCharacterDetails(it: Response<CharacterDetails>) {
        when(it)
        {
            is Response.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Response.Success -> {
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    //Toast.makeText(this@DetailsActivity, it.name, Toast.LENGTH_LONG).show()

                    if (it.image != null) {
                        binding.image.load(it.image)
                    }
                    if (it.name != null) {
                        binding.name.text = "Name: ${it.name}"
                    }
                    if (it.status != null) {
                        binding.status.text = "Status: ${it.status}"
                    }
                    if (it.species != null) {
                        binding.species.text = "Species: ${it.species}"
                    }
                    if (it.gender != null) {
                        binding.gender.text ="Gender: ${it.gender}"
                    }
                    if (it.location != null) {
                        binding.location.text ="Location: ${it.location.name}"
                    }
                    if (it.origin != null) {
                        binding.origin.text ="Origin: ${it.origin.name}"
                    }

                    Log.d("characterDetails", it.toString()) }

            }
            is Response.Error -> {
                it.errorMessage
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@DetailsActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }
}