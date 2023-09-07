package com.priem.multiverseofrickandmorty.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.priem.multiverseofrickandmorty.databinding.ActivityDetailsBinding
import com.priem.multiverseofrickandmorty.model.characterdetails.CharacterDetails
import com.priem.multiverseofrickandmorty.repository.Response
import com.priem.multiverseofrickandmorty.viewmodels.CharacterDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    private fun getCharacterDetails(characterId: Int) {
        characterDetailsViewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
        characterDetailsViewModel.fetchCharacterDetails(characterId)
        characterDetailsViewModel.characterDetails.observe(this, Observer {
            initCharacterDetails(it)
        })
    }

    //initialize character details view
    private fun initCharacterDetails(it: Response<CharacterDetails>) {
        when (it) {
            is Response.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is Response.Success -> {
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    if (it.image != null) {
                        Glide.with(this)
                            .load(it.image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(binding.image)
                    }
                    if (it.name != null) {
                        binding.nameValue.text = it.name
                    }
                    if (it.status != null) {
                        binding.statusValue.text = it.status
                    }
                    if (it.species != null) {
                        binding.speciesValue.text = it.species
                    }
                    if (it.gender != null) {
                        binding.genderValue.text = it.gender
                    }
                    if (it.location != null) {
                        binding.locationValue.text = it.location.name
                    }
                    if (it.origin != null) {
                        binding.originValue.text = it.origin.name
                    }

                    Log.d("characterDetails", it.toString())
                }

            }

            is Response.Error -> {
                it.errorMessage
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@DetailsActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }
}