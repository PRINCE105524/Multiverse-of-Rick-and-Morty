package com.priem.multiverseofrickandmorty.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.priem.multiverseofrickandmorty.ui.DetailsActivity
import com.priem.multiverseofrickandmorty.R
import com.priem.multiverseofrickandmorty.databinding.ListItemBinding
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.models.characterlist.Result

class RecyclerAdapter(private val context: Context, private val characterList: CharacterList?) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var character = characterList?.results?.get(position)
        holder.bind(character)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("characterId", character?.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return characterList?.results?.size!!
    }

    fun getStatusDotResource(status: String): Int {
        return when (status) {
            ("alive").lowercase() -> R.drawable.green_dot
            ("dead").lowercase() -> R.drawable.red_dot
            ("unknown").lowercase() -> R.drawable.brown_dot
            else -> R.drawable.default_dot // Use a default dot or handle unknown status as needed
        }
    }

    inner class ViewHolder(var listItemBinding: ListItemBinding) : RecyclerView.ViewHolder(listItemBinding.root) {

        fun bind(character: Result?) {
            val statusDotResource = getStatusDotResource(character?.status?.lowercase()!!)
            Glide.with(listItemBinding.root)
                .load(character.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listItemBinding.image)
            listItemBinding.name.text = (character.name)
            listItemBinding.statusImageView.setImageResource(statusDotResource)
        }
    }

}