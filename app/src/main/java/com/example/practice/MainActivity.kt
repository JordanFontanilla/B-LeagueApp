package com.example.practice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        // Initialize bottomNavigationView and fab
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        fab = findViewById(R.id.fab)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, HomeFragment())
                .commit()
        }

        // Setup bottom navigation
        setupBottomNavigation()

        // Setup FAB
        setupFloatingActionButton()
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.games -> {
                    replaceFragment(GamesFragment())
                    true
                }
                R.id.skills -> {
                    replaceFragment(SkillsFragment())
                    true
                }
                R.id.schedule -> {
                    replaceFragment(ScheduleFragment())
                    true
                }
                else -> false
            }
        }

        // Ensure default selection
        bottomNavigationView.selectedItemId = R.id.home
    }

    private fun setupFloatingActionButton() {
        fab.setOnClickListener {
            Toast.makeText(this, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}