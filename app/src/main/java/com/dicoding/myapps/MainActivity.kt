package com.dicoding.myapps

import android.content.Intent
import android.os.Bundle
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
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: Adapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i])
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
