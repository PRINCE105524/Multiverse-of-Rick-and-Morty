package com.priem.multiverseofrickandmorty.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.priem.multiverseofrickandmorty.RickyandMortyApplication
import com.priem.multiverseofrickandmorty.adapter.RecyclerAdapter
import com.priem.multiverseofrickandmorty.databinding.ActivityMainBinding
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.repository.Response
import com.priem.multiverseofrickandmorty.viewmodels.characterlistviewmodel.CharacterListViewModel
import com.priem.multiverseofrickandmorty.viewmodels.characterlistviewmodel.CharacterListViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerAdapter
    lateinit var characterListViewModel: CharacterListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacterList()
    }


    //characterList
    private fun getCharacterList() {

        val repositoryCharacterList = (application as RickyandMortyApplication).characterListRepository
        characterListViewModel = ViewModelProvider(this, CharacterListViewModelFactory(repositoryCharacterList)).get(
            CharacterListViewModel::class.java)
        characterListViewModel.characterList.observe(this, Observer {
            initAdapter(it)
        })
    }

    private fun initAdapter(it: Response<CharacterList>) {
        when(it)
        {
            is Response.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Response.Success -> {
                if (it.data != null) {
                    adapter = RecyclerAdapter(this,it.data)
                    binding.recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            is Response.Error -> {
                it.errorMessage
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

}
