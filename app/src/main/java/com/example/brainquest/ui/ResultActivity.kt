package com.example.brainquest.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.brainquest.MainActivity
import com.example.brainquest.R
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.ActivityResultBinding

// Quiz result
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var progress: ProgressManager
    private var newBadgeLines = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progress = ProgressManager(this)
        val subject = LearningRepository.subject(intent.getStringExtra(Extras.SUBJECT_ID))
        val correct = intent.getIntExtra(Extras.CORRECT, 0)
        val total = intent.getIntExtra(Extras.TOTAL, 1).coerceAtLeast(1)
        val points = intent.getIntExtra(Extras.POINTS, 0)
        val bestStreak = intent.getIntExtra(Extras.BEST_STREAK, 0)
        val percent = correct * 100 / total
        val stars = ProgressManager.starsFor(percent)

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = getString(R.string.quiz_result)
        supportActionBar?.subtitle = "${subject.name} Quiz"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Save result
        if (savedInstanceState == null) {
            val before = progress.unlockedBadgeIds()
            progress.recordQuiz(subject.id, correct, total, points)
            newBadgeLines = ArrayList(
                progress.badges().filter { it.unlocked && it.id !in before }.map { "${it.emoji}  ${it.title}" }
            )
        } else {
            newBadgeLines = savedInstanceState.getStringArrayList(STATE_BADGES) ?: arrayListOf()
        }

        // Headline
        val (headline, message) = when {
            percent >= 90 -> "Outstanding! 🏆" to "You are a ${subject.name} superstar!"
            percent >= 70 -> "Great job! 🎉" to "Very good work. A little more practice for 3 stars!"
            percent >= 50 -> "Good effort! 👍" to "You passed. Review the lessons to improve your score."
            else -> "Keep trying! 💪" to "Read the lessons again and try the practice questions."
        }
        binding.textHeadline.text = headline
        binding.textMessage.text = message
        binding.imageTrophy.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(this, if (percent >= 50) R.color.accent else R.color.locked)
        )

        // Score ring
        val scoreColor = ContextCompat.getColor(this, subject.colorRes)
        binding.progressScore.setIndicatorColor(scoreColor)
        binding.progressScore.trackColor = ContextCompat.getColor(this, subject.lightColorRes)
        binding.progressScore.max = 100
        binding.progressScore.setProgressCompat(percent, true)
        binding.textPercent.text = "$percent%"

        // Stars
        listOf(binding.star1, binding.star2, binding.star3).forEachIndexed { i, star ->
            val earned = i < stars
            star.setImageResource(if (earned) R.drawable.ic_star else R.drawable.ic_star_border)
            star.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(this, if (earned) R.color.accent else R.color.locked)
            )
            if (earned) {
                star.scaleX = 0f
                star.scaleY = 0f
                star.animate().scaleX(1f).scaleY(1f).setStartDelay(300L + i * 250L).setDuration(400).start()
            }
        }

        binding.textCorrect.text = "$correct/$total"
        binding.textPointsEarned.text = "+$points"
        binding.textBestStreak.text = "🔥 $bestStreak"

        if (newBadgeLines.isNotEmpty()) {
            binding.cardNewBadges.visibility = View.VISIBLE
            binding.textNewBadges.text = newBadgeLines.joinToString("\n")
        }

        binding.buttonTryAgain.setOnClickListener {
            // Explicit intent
            val explicitIntent = Intent(this, QuizActivity::class.java)
            explicitIntent.putExtra(Extras.SUBJECT_ID, subject.id)
            startActivity(explicitIntent)
            finish()
        }

        binding.buttonShare.setOnClickListener {
            // Implicit intent
            shareText(
                "My BrainQuest result",
                "I scored $percent% (${starText(stars)}) in the ${subject.name} quiz on BrainQuest and earned $points points! 🧠"
            )
        }

        binding.buttonHome.setOnClickListener { goHome() }
    }

    // Explicit intent
    private fun goHome() {
        val explicitIntent = Intent(this, MainActivity::class.java)
        explicitIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(explicitIntent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(STATE_BADGES, newBadgeLines)
    }

    override fun onSupportNavigateUp(): Boolean {
        goHome()
        return true
    }

    companion object {
        private const val STATE_BADGES = "state_badges"
    }
}
