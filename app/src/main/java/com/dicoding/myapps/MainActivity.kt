package com.dicoding.myapps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var distanceList: Array<String>
    lateinit var numberofMoonList: Array<String>
    lateinit var sizePlanetList: Array<String>
    lateinit var orbitPeriod: Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: Adapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menu Button


        //About me button
        val btnMoveABout: Button = findViewById(R.id.about_page)
        btnMoveABout.setOnClickListener(this)

        // Setting up data
        imageList = arrayOf(
            R.drawable.mercury,
            R.drawable.venus,
            R.drawable.earth,
            R.drawable.mars,
            R.drawable.jupiter,
            R.drawable.saturn,
            R.drawable.uranus,
            R.drawable.neptune,
            R.drawable.pluto,
            R.drawable.ceres
        )

        titleList = arrayOf(
            "Mercury",
            "Venus",
            "Earth",
            "Mars",
            "Jupiter",
            "Saturn",
            "Uranus",
            "Neptune",
            "Pluto",
            "Ceres"
        )

        descList = arrayOf(
            getString(R.string.mercury),
            getString(R.string.venus),
            getString(R.string.earth),
            getString(R.string.mars),
            getString(R.string.jupiter),
            getString(R.string.saturn),
            getString(R.string.uranus),
            getString(R.string.neptune),
            getString(R.string.pluto),
            getString(R.string.ceres)
        )

        detailImageList = arrayOf(
            R.drawable.mercury_detail,
            R.drawable.venus_detail,
            R.drawable.earth_detail,
            R.drawable.mars_detail,
            R.drawable.jupiter_detail,
            R.drawable.saturn_detail,
            R.drawable.uranus_detail,
            R.drawable.neptune_detail,
            R.drawable.pluto_detail,
            R.drawable.ceres_detail
        )
        distanceList = arrayOf(
            getString(R.string.planet_mercury_distance_from_earth),
            getString(R.string.planet_venus_distance_from_earth),
            getString(R.string.planet_earth_distance_from_earth),
            getString(R.string.planet_jupiter_distance_from_earth),
            getString(R.string.planet_saturn_distance_from_earth),
            getString(R.string.planet_saturn_distance_from_earth),
            getString(R.string.planet_uranus_distance_from_earth),
            getString(R.string.planet_neptune_distance_from_earth),
            getString(R.string.planet_pluto_distance_from_earth),
            getString(R.string.planet_ceres_distance_from_earth),
        )
        numberofMoonList = arrayOf(
            getString(R.string.planet_mercury_moons),
            getString(R.string.planet_venus_moons),
            getString(R.string.planet_earth_moons),
            getString(R.string.planet_mars_moons),
            getString(R.string.planet_jupiter_moons),
            getString(R.string.planet_saturn_moons),
            getString(R.string.planet_uranus_moons),
            getString(R.string.planet_neptune_moons),
            getString(R.string.planet_pluto_moons),
            getString(R.string.planet_ceres_moons)
        )

        sizePlanetList = arrayOf(
            getString(R.string.planet_mercury_size),
            getString(R.string.planet_venus_size),
            getString(R.string.planet_earth_size),
            getString(R.string.planet_mars_size),
            getString(R.string.planet_jupiter_size),
            getString(R.string.planet_saturn_size),
            getString(R.string.planet_uranus_size),
            getString(R.string.planet_neptune_size),
            getString(R.string.planet_pluto_size),
            getString(R.string.planet_ceres_size)
        )

        orbitPeriod = arrayOf(
            getString(R.string.planet_mercury_orbital_period),
            getString(R.string.planet_venus_orbital_period),
            getString(R.string.planet_earth_orbital_period),
            getString(R.string.planet_mars_orbital_period),
            getString(R.string.planet_jupiter_orbital_period),
            getString(R.string.planet_saturn_orbital_period),
            getString(R.string.planet_uranus_orbital_period),
            getString(R.string.planet_neptune_orbital_period),
            getString(R.string.planet_pluto_orbital_period),
            getString(R.string.planet_ceres_orbital_period)
        )


        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        searchList = arrayListOf()
        getData()

        // SearchView listener
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText?.toLowerCase(Locale.getDefault()) ?: ""
                searchList.clear()
                if (searchText.isNotEmpty()) {
                    dataList.forEach {
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                } else {
                    searchList.addAll(dataList)
                }
                myAdapter.notifyDataSetChanged()
                return false
            }
        })

        // Set adapter
        myAdapter = Adapter(searchList)
        recyclerView.adapter = myAdapter

        // Handle item click
        myAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", selectedData)
            startActivity(intent)
        }


        // Window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    // Get data for RecyclerView
    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i], distanceList[i], numberofMoonList[i], sizePlanetList[i], orbitPeriod[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.about_page->{
                val movePagetoAboutMe = Intent(this@MainActivity, aboutMe::class.java)
                startActivity(movePagetoAboutMe)
            }
        }
    }
}
