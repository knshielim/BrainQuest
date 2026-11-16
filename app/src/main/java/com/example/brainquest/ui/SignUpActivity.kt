package com.example.brainquest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.brainquest.R
import com.example.brainquest.data.AuthManager
import com.example.brainquest.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main)

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
        val intent = Intent(this, ProfileActivity::class.java).putExtra(Extras.FIRST_RUN, true)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
