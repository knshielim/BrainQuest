package com.example.brainquest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.brainquest.R
import com.example.brainquest.data.AuthManager
import com.example.brainquest.databinding.ActivitySignupBinding

// Sign up
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSignUp.setOnClickListener { signUp() }

        binding.textGoLogin.setOnClickListener { finish() }
    }

    private fun signUp() {
        val name = binding.editName.text?.toString().orEmpty()
        val email = binding.editEmail.text?.toString().orEmpty()
        val password = binding.editPassword.text?.toString().orEmpty()
        val confirm = binding.editConfirm.text?.toString().orEmpty()

        listOf(binding.inputName, binding.inputEmail, binding.inputPassword, binding.inputConfirm).forEach { it.error = null }
        val error = AuthManager(this).signUp(name, email, password, confirm)
        if (error != null) {
            val field = when {
                error.contains("name") -> binding.inputName
                error.contains("e-mail") -> binding.inputEmail
                error.contains("match") -> binding.inputConfirm
                else -> binding.inputPassword
            }
            field.error = error
            return
        }

        Toast.makeText(this, R.string.signup_success, Toast.LENGTH_SHORT).show()
        // Explicit intent
        val explicitIntent = Intent(this, ProfileActivity::class.java).putExtra(Extras.FIRST_RUN, true)
        explicitIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(explicitIntent)
    }
}
