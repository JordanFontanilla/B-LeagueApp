package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SkillsFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var burgerMenu: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fab: FloatingActionButton

    private lateinit var skillsProgressBars: List<ProgressBar>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.skills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        initializeViews(view)

        // Setup progress views
        setupProgressViews()

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
            fab = requireActivity().findViewById(R.id.games_fab)

            // Skills ProgressBars
            skillsProgressBars = listOf(
                view.findViewById(R.id.dribbling_progress_bar),
                view.findViewById(R.id.passing_progress_bar),
                view.findViewById(R.id.defense_progress_bar),
                view.findViewById(R.id.playmaking_progress_bar),
                view.findViewById(R.id.shooting_progress_bar)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error initializing views", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupProgressViews() {
        // Corresponding progress percentages
        val progressPercentages = listOf(75, 85, 70, 80, 75)

        // Set progress for each bar
        skillsProgressBars.forEachIndexed { index, progressBar ->
            progressBar.progress = progressPercentages[index]
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

        fab.setOnClickListener {
            // Handle FAB click (e.g., add or edit skills)
            Toast.makeText(requireContext(), "Edit skills", Toast.LENGTH_SHORT).show()
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
                    // Already in Skills fragment, do nothing
                    true
                }
                R.id.schedule -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, ScheduleFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun newInstance(): SkillsFragment {
            return SkillsFragment()
        }
    }
}