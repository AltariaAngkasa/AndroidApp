package com.dicoding.myapps

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash_activity : AppCompatActivity() {
    // Durasi splash screen dalam milidetik (contoh: 3 detik)
    private val splashTimeOut: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // Pengaturan padding untuk insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fungsi untuk menunda eksekusi dan berpindah ke MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Pindah ke MainActivity setelah durasi splashTimeOut
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Agar tidak kembali ke splash screen saat menekan tombol "back"
        }, splashTimeOut)
    }
}
