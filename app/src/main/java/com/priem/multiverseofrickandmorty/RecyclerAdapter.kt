package com.priem.multiverseofrickandmorty

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.priem.multiverseofrickandmorty.databinding.ListItemBinding
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.models.characterlist.Result

class RecyclerAdapter(private val context: Context, private val characterList: CharacterList?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var character = characterList?.results?.get(position)
        holder.bind(character)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra("characterId", character?.id);
            //intent.putExtra(DetailsActivity.INTENT_PARCELABLE,name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return characterList?.results?.size!!
    }

    inner class ViewHolder(var listItemBinding: ListItemBinding): RecyclerView.ViewHolder(listItemBinding.root){
        fun bind(character: Result?){
            listItemBinding.image.load(character?.image)
            listItemBinding.name.text = (character?.name)
            listItemBinding.status.text = (character?.status)
        }
    }



}