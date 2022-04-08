package com.example.starwarsplanet.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsplanet.R
import com.example.starwarsplanet.databinding.PlanetListBinding
import com.example.starwarsplanet.model.PlanetData
import com.example.starwarsplanet.view.NewActivity

class PlanetsAdapter(

    var c: Context, var planetList: ArrayList<PlanetData>
) : RecyclerView.Adapter<PlanetsAdapter.PlanetViewHolder>() {

    inner class PlanetViewHolder(var v: PlanetListBinding) : RecyclerView.ViewHolder(v.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<PlanetListBinding>(
            inflater, R.layout.planet_list, parent, false)
        return PlanetViewHolder(v)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val newList = planetList[position]
        holder.v.isPlanets = planetList[position]
        holder.v.root.setOnClickListener {
            val planetImage = newList.planetImage
            val planetName = newList.planetName
            val planetType = newList.planetType
            val planetDescription = newList.planetDescription

            val mIntent = Intent(c, NewActivity::class.java)
            mIntent.putExtra("planetImage", planetImage)
            mIntent.putExtra("planetName", planetName)
            mIntent.putExtra("planetType", planetType)
            mIntent.putExtra("planetDescription", planetDescription)
            c.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return planetList.size
    }
}