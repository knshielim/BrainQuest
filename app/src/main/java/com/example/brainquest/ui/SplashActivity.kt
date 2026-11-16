package com.example.brainquest.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.OvershootInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.brainquest.MainActivity
import com.example.brainquest.R
import com.example.brainquest.data.AuthManager
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.ActivitySplashBinding

// Splash screen
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    private val openNextScreen = Runnable {
        // Explicit intent
        val explicitIntent = when {
            !AuthManager(this).isLoggedIn -> Intent(this, LoginActivity::class.java)
            ProgressManager(this).hasProfile() -> Intent(this, MainActivity::class.java)
            else -> Intent(this, ProfileActivity::class.java).putExtra(Extras.FIRST_RUN, true)
        }
        startActivity(explicitIntent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playIntroAnimation()
        handler.postDelayed(openNextScreen, SPLASH_DELAY_MS)
    }

    private fun playIntroAnimation() {
        binding.logoContainer.apply {
            alpha = 0f
            scaleX = 0.5f
            scaleY = 0.5f
            animate().alpha(1f).scaleX(1f).scaleY(1f)
                .setDuration(700).setInterpolator(OvershootInterpolator()).start()
        }
        listOf(binding.textAppName, binding.textTagline, binding.textSubtitle).forEachIndexed { i, view ->
            view.alpha = 0f
            view.translationY = 40f
            view.animate().alpha(1f).translationY(0f)
                .setStartDelay(350L + i * 150L).setDuration(500).start()
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(openNextScreen)
        super.onDestroy()
    }

    companion object {
        private const val SPLASH_DELAY_MS = 2500L
    }
}
