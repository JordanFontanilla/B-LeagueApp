package com.example.practice

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var heightInput: TextInputEditText
    private lateinit var weightInput: TextInputEditText
    private lateinit var birthdayInput: TextInputEditText
    private lateinit var skillLevelDropdown: AutoCompleteTextView
    private lateinit var locationInput: TextInputEditText
    private lateinit var birthdayInputLayout: TextInputLayout
    private lateinit var locationInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        initializeViews()
        setupSkillLevelDropdown()
        setupClickListeners()
    }

    private fun initializeViews() {
        heightInput = findViewById(R.id.heightInput)
        weightInput = findViewById(R.id.weightInput)
        birthdayInput = findViewById(R.id.birthdayInput)
        skillLevelDropdown = findViewById(R.id.skillLevelDropdown)
        locationInput = findViewById(R.id.locationInput)
        birthdayInputLayout = findViewById(R.id.birthdayInputLayout)
        locationInputLayout = findViewById(R.id.locationInputLayout)

        // Setup back button
        findViewById<android.widget.ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }

        // Setup register button
        findViewById<com.google.android.material.button.MaterialButton>(R.id.registerButton).setOnClickListener {
            handleRegistration()
        }
    }

    private fun setupSkillLevelDropdown() {
        val skillLevels = arrayOf("Beginner", "Intermediate", "Advanced", "Expert")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, skillLevels)
        skillLevelDropdown.setAdapter(adapter)
    }

    private fun setupClickListeners() {
        // Birthday picker
        birthdayInputLayout.setEndIconOnClickListener {
            showDatePicker()
        }
        birthdayInput.setOnClickListener {
            showDatePicker()
        }

        // Location picker
        locationInputLayout.setEndIconOnClickListener {
            // TODO: Implement location picker
            Toast.makeText(this, "Location picker to be implemented", Toast.LENGTH_SHORT).show()
        }
        locationInput.setOnClickListener {
            // TODO: Implement location picker
            Toast.makeText(this, "Location picker to be implemented", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                birthdayInput.setText(dateFormat.format(calendar.time))
            },
            year,
            month,
            day
        ).show()
    }

    private fun handleRegistration() {
        // Validate inputs
        val height = heightInput.text.toString()
        val weight = weightInput.text.toString()
        val birthday = birthdayInput.text.toString()
        val skillLevel = skillLevelDropdown.text.toString()
        val location = locationInput.text.toString()

        if (height.isEmpty() || weight.isEmpty() || birthday.isEmpty() ||
            skillLevel.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Implement actual registration logic
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        finish()
    }
}