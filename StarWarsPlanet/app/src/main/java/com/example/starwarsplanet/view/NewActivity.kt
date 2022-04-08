package com.example.starwarsplanet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwarsplanet.R
import com.example.starwarsplanet.viewmodel.getProgressDrawable
import com.example.starwarsplanet.viewmodel.loadImage
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.planet_list.*
import kotlinx.android.synthetic.main.planet_list.planetName
import kotlinx.android.synthetic.main.planet_list.planetType

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val planetIntent =  intent
        val planetNameNew = planetIntent.getStringExtra("planetName")
        val planetTypeNew = planetIntent.getStringExtra("planetType")
        val planetDescriptionNew = planetIntent.getStringExtra("planetDescription")
        val planetImageNew = planetIntent.getStringExtra("planetImage")

        planetName.text = planetNameNew
        planetType.text = planetTypeNew
        planetDescription.text = planetDescriptionNew
        planetImage.loadImage(planetImageNew, getProgressDrawable(this))
    }
}