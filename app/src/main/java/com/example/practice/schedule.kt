package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var burgerMenu: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var gamesAdapter: GameAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        initializeViews(view)

        // Setup RecyclerView
        setupRecyclerView()

        // Setup click listeners
        setupClickListeners()

        // Setup bottom navigation
        setupBottomNavigation()
    }

    private fun initializeViews(view: View) {
        try {
            backButton = view.findViewById(R.id.schedule_back_button)
            burgerMenu = view.findViewById(R.id.schedule_burger_menu)
            bottomNavigationView = requireActivity().findViewById(R.id.schedule_bottom_navigation_view)
            recyclerView = view.findViewById(R.id.schedule_games_list)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error initializing views", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        // Generate sample games
        val sampleGames = generateSampleGames()
        gamesAdapter = GameAdapter(sampleGames)
        recyclerView.adapter = gamesAdapter
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

    private fun generateSampleGames(): List<Game> {
        return listOf(
            Game(
                dateTime = "7:30 PM • Dec 15",
                homeTeam = "Lakers",
                awayTeam = "Warriors",
                location = "Staples Center, Los Angeles",
                status = "UPCOMING"
            ),
            Game(
                dateTime = "8:00 PM • Dec 15",
                homeTeam = "Celtics",
                awayTeam = "Nets",
                location = "TD Garden, Boston",
                status = "UPCOMING"
            ),
            // ... rest of the sample games remain the same
            Game(
                dateTime = "7:30 PM • Dec 15",
                homeTeam = "Pacers",
                awayTeam = "Cavaliers",
                location = "Gainbridge Fieldhouse, Indianapolis",
                status = "UPCOMING"
            )
        )
    }

    companion object {
        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}