package edu.udem.primerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val fab = findViewById<FloatingActionButton>(R.id.fabBtn)
        fab.setOnClickListener { createNote() }

        val navController = findNavController(R.id.myNavHostFragment)

        findViewById<BottomNavigationView>(R.id.bottom_nav).setupWithNavController(navController)
    }

    private fun createNote() {
        val intent = Intent(this, EditNoteActivity::class.java)
        startActivity(intent)
    }
}