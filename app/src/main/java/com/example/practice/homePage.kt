package com.example.practice

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var editButton: ImageView
    private lateinit var backButton: ImageView
    private lateinit var burgerMenu: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        initializeViews(view)

        // Setup gallery launcher
        setupGalleryLauncher()

        // Setup click listeners
        setupClickListeners()

        // Setup bottom navigation
        setupBottomNavigation()
    }

    private fun initializeViews(view: View) {
        try {
            profileImage = view.findViewById(R.id.profileImage)
            editButton = view.findViewById(R.id.penCustomize)
            backButton = view.findViewById(R.id.backButton)
            burgerMenu = view.findViewById(R.id.burgerHome)
            bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error initializing views", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        editButton.setOnClickListener { checkGalleryPermission() }
        backButton.setOnClickListener {
            // Instead of finish(), pop back stack or handle fragment navigation
            requireActivity().supportFragmentManager.popBackStack()
        }
        burgerMenu.setOnClickListener {
            // TODO: Implement menu functionality
            Toast.makeText(requireContext(), "Menu clicked", Toast.LENGTH_SHORT).show()
        }


    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, HomeFragment())
                        .commit()
                    true
                }
                R.id.games -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, GamesFragment())
                        .commit()
                    true
                }
                R.id.skills -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SkillsFragment())
                        .commit()
                    true
                }
                R.id.schedule -> {
                    // Already in Schedule fragment, do nothing
                    true
                }
                R.id.gameStart -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, GameStartFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
        // Add direct click listener for the gameStart ImageView
    }


    private fun setupGalleryLauncher() {
        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            uri?.let {
                selectedImageUri = it
                profileImage.setImageURI(it)
                Toast.makeText(requireContext(), "Profile picture updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkGalleryPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                openGallery()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                Toast.makeText(requireContext(), "Storage permission is required to select a profile picture", Toast.LENGTH_LONG).show()
                requestStoragePermission()
            }
            else -> {
                requestStoragePermission()
            }
        }
    }

    private fun requestStoragePermission() {
        requestPermissions(
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
                    Toast.makeText(requireContext(), "Permission denied. Cannot select profile picture.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 1001

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}