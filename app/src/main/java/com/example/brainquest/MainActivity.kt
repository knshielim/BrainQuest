package com.example.brainquest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.brainquest.R
import com.example.brainquest.data.AuthManager
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.ActivityMainBinding
import com.example.brainquest.ui.HomeFragment
import com.example.brainquest.ui.ProgressFragment
import com.example.brainquest.ui.RewardsFragment
import com.example.brainquest.ui.LoginActivity
import com.example.brainquest.ui.ProfileActivity
import com.example.brainquest.ui.shareText
import com.example.brainquest.ui.openUrl
import com.example.brainquest.ui.sendEmail
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// Home screen
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var progress: ProgressManager
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progress = ProgressManager(this)
        setSupportActionBar(binding.appBarMain.toolbar)

        // Bottom navigation
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_learn -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_progress -> {
                    replaceFragment(ProgressFragment())
                    true
                }
                R.id.bottom_rewards -> {
                    replaceFragment(RewardsFragment())
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Back button
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (bottomNavigationView.selectedItemId != R.id.bottom_learn) {
                    bottomNavigationView.selectedItemId = R.id.bottom_learn
                } else {
                    finish()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateTitle()
    }

    private fun updateTitle() {
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.subtitle = "Level ${progress.level} ${progress.levelTitle} • ${progress.totalPoints} pts"
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    // Options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                // Explicit intent
                val explicitIntent = Intent(this, ProfileActivity::class.java)
                startActivity(explicitIntent)
                return true
            }
            R.id.action_share -> {
                // Implicit intent
                shareText(
                    getString(R.string.app_name),
                    "I'm revising 12 Cambridge IGCSE subjects on BrainQuest! " +
                            "I'm Level ${progress.level} with ${progress.totalPoints} points. Can you beat me? 🧠🏆"
                )
                return true
            }
            R.id.action_help -> {
                // Implicit intent
                openUrl("https://www.khanacademy.org/")
                return true
            }
            R.id.action_contact -> {
                // Implicit intent
                sendEmail(
                    "teacher@school.edu.my",
                    "BrainQuest – Question from ${progress.name}",
                    "Hello Teacher,\n\nI have a question about my BrainQuest lessons:\n\n\nFrom: ${progress.name} (${progress.form}, ${progress.school})"
                )
                return true
            }
            R.id.action_about -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.about_title)
                    .setMessage(R.string.about_msg)
                    .setPositiveButton(R.string.close, null)
                    .show()
                return true
            }
            R.id.action_reset -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.reset_title)
                    .setMessage(R.string.reset_msg)
                    .setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.reset) { _, _ ->
                        progress.resetProgress()
                        Toast.makeText(this, R.string.reset_done, Toast.LENGTH_SHORT).show()
                        updateTitle()
                        bottomNavigationView.selectedItemId = R.id.bottom_learn
                    }
                    .show()
                return true
            }
            R.id.action_logout -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.logout_title)
                    .setMessage(R.string.logout_msg)
                    .setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.logout) { _, _ ->
                        AuthManager(this).logout()
                        // Explicit intent
                        val explicitIntent = Intent(this, LoginActivity::class.java)
                        explicitIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(explicitIntent)
                    }
                    .show()
                return true
            }
            R.id.action_exit -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.exit_title)
                    .setMessage(R.string.exit_msg)
                    .setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.exit) { _, _ -> finishAffinity() }
                    .show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
