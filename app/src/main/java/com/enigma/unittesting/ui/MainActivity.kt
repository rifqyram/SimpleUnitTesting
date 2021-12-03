package com.enigma.unittesting.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enigma.unittesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            goToSecond.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondaryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}