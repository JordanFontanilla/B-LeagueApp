package com.example.practice

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class homePage : AppCompatActivity() {  // Changed class name to follow Kotlin naming conventions

    private lateinit var profileImage: ImageView
    private lateinit var editButton: ImageView
    private lateinit var backButton: ImageView
    private lateinit var burgerMenu: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fab: FloatingActionButton  // Added FAB

    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)  // Changed to match your layout file name

        // Initialize views
        initializeViews()

        // Setup gallery launcher
        setupGalleryLauncher()

        // Setup click listeners
        setupClickListeners()

        // Setup bottom navigation
        setupBottomNavigation()
    }

    private fun initializeViews() {
        try {
            profileImage = findViewById(R.id.profileImage)
            editButton = findViewById(R.id.penCustomize)
            backButton = findViewById(R.id.backButton)
            burgerMenu = findViewById(R.id.burgerHome)
            bottomNavigationView = findViewById(R.id.bottomNavigationView)
            fab = findViewById(R.id.fab)  // Initialize FAB
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error initializing views", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        editButton.setOnClickListener { checkGalleryPermission() }
        backButton.setOnClickListener { finish() }
        burgerMenu.setOnClickListener {
            // TODO: Implement menu functionality
        }

        fab.setOnClickListener {
            // Handle FAB click
            Toast.makeText(this, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.games -> {
                    Toast.makeText(this, "Games", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.skills -> {
                    Toast.makeText(this, "Skills", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.schedule -> {
                    Toast.makeText(this,"Schedule" , Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Set default selected item
        bottomNavigationView.selectedItemId = R.id.home
    }

    private fun setupGalleryLauncher() {
        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            uri?.let {
                selectedImageUri = it
                profileImage.setImageURI(it)
                Toast.makeText(this, "Profile picture updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkGalleryPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                openGallery()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                Toast.makeText(this, "Storage permission is required to select a profile picture", Toast.LENGTH_LONG).show()
                requestStoragePermission()
            }
            else -> {
                requestStoragePermission()
            }
        }
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    Toast.makeText(this, "Permission denied. Cannot select profile picture.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 1001
    }
}