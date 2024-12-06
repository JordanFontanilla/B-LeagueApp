package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GamesFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var burgerMenu: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        initializeViews(view)

        // Setup click listeners
        setupClickListeners()

        // Setup bottom navigation
        setupBottomNavigation()
    }

    private fun initializeViews(view: View) {
        try {
            backButton = view.findViewById(R.id.games_back_button)
            burgerMenu = view.findViewById(R.id.games_burger_menu)
            bottomNavigationView = requireActivity().findViewById(R.id.games_bottom_navigation_view)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error initializing views", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            // Navigate back or close fragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        burgerMenu.setOnClickListener {
            // TODO: Implement burger menu functionality
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
    }

    companion object {
        fun newInstance(): GamesFragment {
            return GamesFragment()
        }
    }
}