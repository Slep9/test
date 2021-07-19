package com.example.marvel.allheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.squareup.picasso.Picasso
import com.example.marvel.data.marvel.Result

class AdapterAllHeroes(list: MutableList<Result>, val listener : ClickHero) : RecyclerView.Adapter<AdapterAllHeroes.AdapterAllHeroesVH>(){

    var list: MutableList<Result> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAllHeroesVH =
            AdapterAllHeroesVH(LayoutInflater.from(parent.context).inflate(R.layout.item_all_heroes, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AdapterAllHeroesVH, position: Int) {
        holder.containerImageView.setOnClickListener { listener.click(list[position]) }
        Picasso.get().load(list[position].thumbnail.path + "." + list[position].thumbnail.extension).into(holder.containerImageView)
    }

    inner class  AdapterAllHeroesVH(view : View): RecyclerView.ViewHolder(view){
        val containerImageView: ImageView = view.findViewById(R.id.picture)
    }
}

