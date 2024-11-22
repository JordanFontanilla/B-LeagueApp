import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import android.util.Patterns
import com.example.practice.R

class usercred : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var createAccountButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.usercred)

        emailInput = findViewById(R.id.emailInput)
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput)
        createAccountButton = findViewById(R.id.createAccountButton)

        auth = FirebaseAuth.getInstance()

        setupListeners()
    }

    private fun setupListeners() {
        findViewById<Button>(R.id.backButton).setOnClickListener {
            onBackPressed()
        }

        createAccountButton.setOnClickListener {
            if (validateInputs()) {
                createAccount()
            }
        }

        emailInput.addTextChangedListener(createTextWatcher { validateEmail() })
        usernameInput.addTextChangedListener(createTextWatcher { validateUsername() })
        passwordInput.addTextChangedListener(createTextWatcher { validatePassword() })
        confirmPasswordInput.addTextChangedListener(createTextWatcher { validatePasswordMatch() })
    }

    private fun createTextWatcher(validationFunc: () -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { validationFunc() }
        }
    }

    private fun validateInputs(): Boolean {
        return validateEmail() && validateUsername() &&
                validatePassword() && validatePasswordMatch()
    }

    private fun validateEmail(): Boolean {
        val email = emailInput.text.toString().trim()

        return when {
            email.isEmpty() -> {
                emailInput.error = "Email is required"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailInput.error = "Please enter a valid email address"
                false
            }
            else -> {
                emailInput.error = null
                true
            }
        }
    }

    private fun validateUsername(): Boolean {
        val username = usernameInput.text.toString().trim()

        return when {
            username.isEmpty() -> {
                usernameInput.error = "Username is required"
                false
            }
            username.length < 3 -> {
                usernameInput.error = "Username must be at least 3 characters long"
                false
            }
            !username.matches(Regex("^[a-zA-Z0-9._-]+$")) -> {
                usernameInput.error = "Username can only contain letters, numbers, dots, underscores, and hyphens"
                false
            }
            else -> {
                usernameInput.error = null
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        val password = passwordInput.text.toString()

        return when {
            password.isEmpty() -> {
                passwordInput.error = "Password is required"
                false
            }
            password.length < 8 -> {
                passwordInput.error = "Password must be at least 8 characters long"
                false
            }
            !password.matches(Regex(".*[A-Z].*")) -> {
                passwordInput.error = "Password must contain at least one uppercase letter"
                false
            }
            !password.matches(Regex(".*[a-z].*")) -> {
                passwordInput.error = "Password must contain at least one lowercase letter"
                false
            }
            !password.matches(Regex(".*[0-9].*")) -> {
                passwordInput.error = "Password must contain at least one number"
                false
            }
            !password.matches(Regex(".*[!@#\$%^&*(),.?\":{}|<>].*")) -> {
                passwordInput.error = "Password must contain at least one special character"
                false
            }
            else -> {
                passwordInput.error = null
                true
            }
        }
    }

    private fun validatePasswordMatch(): Boolean {
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        return when {
            confirmPassword.isEmpty() -> {
                confirmPasswordInput.error = "Please confirm your password"
                false
            }
            password != confirmPassword -> {
                confirmPasswordInput.error = "Passwords do not match"
                false
            }
            else -> {
                confirmPasswordInput.error = null
                true
            }
        }
    }

    private fun createAccount() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString()
        val username = usernameInput.text.toString().trim()

        // Show loading state
        createAccountButton.isEnabled = false
        createAccountButton.text = "Creating Account..."

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Update profile with username
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileTask ->
                            if (profileTask.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish() // Return to previous activity
                            } else {
                                // Reset button state
                                createAccountButton.isEnabled = true
                                createAccountButton.text = "Create Account"
                                Toast.makeText(
                                    this,
                                    "Failed to update profile: ${profileTask.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    // Reset button state
                    createAccountButton.isEnabled = true
                    createAccountButton.text = "Create Account"
                    Toast.makeText(
                        this,
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}