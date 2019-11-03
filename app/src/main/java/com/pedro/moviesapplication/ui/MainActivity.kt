package com.pedro.moviesapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.pedro.moviesapplication.R

class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.navHostMain) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
