package com.dicoding.myapps

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengambil data dari Intent
        val getData = intent.getParcelableExtra<DataClass>("android")
        if (getData != null) {
            // Inisialisasi view
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val detailImage: ImageView = findViewById(R.id.detailImage)
            val distance: TextView = findViewById(R.id.distance)
            val numberOfMoon: TextView = findViewById(R.id.number_of_moon)
            val planetSize: TextView = findViewById(R.id.planetSize)
            val orbitPeriod: TextView = findViewById(R.id.orbitPeriod)

            // Mengisi data ke views
            detailTitle.text = getData.dataTitle
            detailDesc.text = getData.dataDesc
            detailImage.setImageResource(getData.dataDetailImage)

            // Mengisi informasi tambahan
            distance.text = getData.distance
            numberOfMoon.text = getData.numberOfMoons
            planetSize.text = getData.size
            orbitPeriod.text = getData.orbitPeriod
        }

        // Atur padding untuk window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
