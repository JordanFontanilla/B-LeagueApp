import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import com.example.practice.R
import java.util.Calendar

class RegistrationActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var titleText: TextView
    private lateinit var heightInput: EditText
    private lateinit var weightInput: EditText
    private lateinit var birthdayInput: EditText
    private lateinit var skillLevelDropdown: AutoCompleteTextView
    private lateinit var locationInput: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        // Find views by ID
        backButton = findViewById(R.id.backButton)
        titleText = findViewById(R.id.titleText)
        heightInput = findViewById(R.id.heightInput)
        weightInput = findViewById(R.id.weightInput)
        birthdayInput = findViewById(R.id.birthdayInput)
        skillLevelDropdown = findViewById(R.id.skillLevelDropdown)
        locationInput = findViewById(R.id.locationInput)
        registerButton = findViewById(R.id.registerButton)

        // Set up back button click listener
        backButton.setOnClickListener { finish() }

        // Set up birthday input
        val calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                birthdayInput.setText(
                    "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
                )
            }
        birthdayInput.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Set up skill level dropdown
        val skillLevels = arrayOf("Beginner", "Intermediate", "Advanced", "Expert")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, skillLevels)
        (skillLevelDropdown as? AutoCompleteTextView)?.setAdapter(adapter)

        // Set up location input click listener
        locationInput.setOnClickListener {
            // Implement location selection logic here
        }

        // Set up register button click listener
        registerButton.setOnClickListener {
            // Implement registration logic here
        }
    }
}