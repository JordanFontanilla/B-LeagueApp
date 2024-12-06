package com.example.practice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GameStartFragment : Fragment() {
    private lateinit var backButton: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    // Game Details TextViews
    private lateinit var gameTimeText: TextView
    private lateinit var locationText: TextView
    private lateinit var playersValueText: TextView
    private lateinit var spotsValueText: TextView
    private lateinit var skillLevelValueText: TextView
    private lateinit var positionNumberText: TextView
    private lateinit var waitTimeText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gamestart, container, false)
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
            // Initialize navigation and menu buttons
            backButton = view.findViewById(R.id.backButton)

            // Change this line to use view.findViewById()
            bottomNavigationView = view.findViewById(R.id.bottomNavigationView)

            // Game Details TextViews
            gameTimeText = view.findViewById(R.id.gameTime)
            locationText = view.findViewById(R.id.locationText)
            playersValueText = view.findViewById(R.id.playersValue)
            spotsValueText = view.findViewById(R.id.spotsValue)
            skillLevelValueText = view.findViewById(R.id.skillLevelValue)
            positionNumberText = view.findViewById(R.id.positionNumber)
            waitTimeText = view.findViewById(R.id.waitTime)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error initializing views: " + e.message, Toast.LENGTH_SHORT).show()
        }
    }


    private fun setupClickListeners() {
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
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
        fun newInstance(): GameStartFragment {
            return GameStartFragment()
        }
    }
}