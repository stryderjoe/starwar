package com.example.starwarsplanet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsplanet.R
import com.example.starwarsplanet.model.PlanetData
import com.example.starwarsplanet.viewmodel.PlanetsAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    private lateinit var planetList: ArrayList<PlanetData>
    private lateinit var mAdapter: PlanetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        planetList = ArrayList()
        mAdapter = PlanetsAdapter(this, planetList)
        recyclerPlanets.layoutManager = LinearLayoutManager(this)
        recyclerPlanets.setHasFixedSize(true)

        getPlanetsData()
    }

    private fun getPlanetsData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Planets")
        mDatabase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (planetSnapshot in snapshot.children) {
                        val planet = planetSnapshot.getValue(PlanetData::class.java)
                        planetList.add(planet!!)
                    }
                    recyclerPlanets.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}