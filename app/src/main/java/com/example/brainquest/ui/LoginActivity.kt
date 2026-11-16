package com.example.brainquest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.brainquest.MainActivity
import com.example.brainquest.R
import com.example.brainquest.data.AuthManager
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main)

        auth = AuthManager(this)

        binding.buttonLogin.setOnClickListener { logIn() }

        // Explicit intent
        binding.textGoSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun logIn() {
        val email = binding.editEmail.text?.toString().orEmpty()
        val password = binding.editPassword.text?.toString().orEmpty()

        binding.inputEmail.error = null
        binding.inputPassword.error = null
        val error = auth.login(email, password)
        if (error != null) {
            if (error.contains("password", ignoreCase = true) && !error.contains("e-mail")) {
                binding.inputPassword.error = error
            } else {
                binding.inputEmail.error = error
            }
            return
        }

        val progress = ProgressManager(this)
        val intent = if (progress.hasProfile()) {
            Toast.makeText(this, getString(R.string.login_success, progress.firstName), Toast.LENGTH_SHORT).show()
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, ProfileActivity::class.java).putExtra(Extras.FIRST_RUN, true)
        }
        startActivity(intent)
        finish()
    }
}
