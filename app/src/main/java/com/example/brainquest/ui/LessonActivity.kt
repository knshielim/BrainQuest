package com.example.brainquest.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.brainquest.R
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.Lesson
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.ActivityLessonBinding
import com.google.android.material.button.MaterialButton

class LessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonBinding
    private lateinit var progress: ProgressManager
    private lateinit var lesson: Lesson
    private val optionButtons = mutableListOf<MaterialButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main, binding.appBarMain.root)

        progress = ProgressManager(this)
        val found = LearningRepository.lesson(intent.getStringExtra(Extras.LESSON_ID))
        if (found == null) {
            finish()
            return
        }
        lesson = found
        val subject = LearningRepository.subject(lesson.subjectId)

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = lesson.title
        supportActionBar?.subtitle = subject.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.imageLesson.setImageResource(lesson.imageRes)
        binding.textTitle.text = lesson.title
        binding.textSummary.text = lesson.summary
        binding.textKeyPoints.text = lesson.keyPoints.joinToString("\n") { "✔  $it" }
        binding.cardKeyPoints.setCardBackgroundColor(ContextCompat.getColor(this, subject.lightColorRes))
        val subjectColor = ContextCompat.getColor(this, subject.colorRes)
        binding.buttonStudy.backgroundTintList = ColorStateList.valueOf(subjectColor)
        binding.buttonTakeQuiz.strokeColor = ColorStateList.valueOf(subjectColor)
        binding.buttonTakeQuiz.setTextColor(subjectColor)
        binding.buttonTakeQuiz.iconTint = ColorStateList.valueOf(subjectColor)
        updateCompletedBadge()

        // Explicit intent
        binding.buttonStudy.setOnClickListener {
            val intent = Intent(this, FlashcardActivity::class.java)
            intent.putExtra(Extras.LESSON_ID, lesson.id)
            startActivity(intent)
        }

        // Explicit intent
        binding.buttonTakeQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra(Extras.SUBJECT_ID, lesson.subjectId)
            startActivity(intent)
        }

        setUpPractice()
    }

    private fun updateCompletedBadge() {
        binding.textCompleted.visibility =
            if (progress.isLessonCompleted(lesson.id)) View.VISIBLE else View.GONE
    }

    private fun setUpPractice() {
        val question = lesson.practice
        binding.textPracticeQuestion.text = question.text
        question.options.forEachIndexed { index, option ->
            val button = layoutInflater.inflate(R.layout.item_option_button, binding.containerOptions, false) as MaterialButton
            button.text = "${'A' + index}.  $option"
            button.setOnClickListener { checkAnswer(index, button) }
            binding.containerOptions.addView(button)
            optionButtons += button
        }
    }

    private fun checkAnswer(index: Int, button: MaterialButton) {
        val question = lesson.practice
        if (index == question.answerIndex) {
            styleOption(button, R.color.correct, R.color.correct_light)
            optionButtons.forEach { it.isEnabled = false }
            button.isEnabled = true
            button.isClickable = false
            showFeedback("🎉 Correct! ${question.explanation}", R.color.correct_light)

            if (progress.markLessonCompleted(lesson.id)) {
                progress.addPoints(ProgressManager.LESSON_POINTS)
                Toast.makeText(this, "Lesson completed! +${ProgressManager.LESSON_POINTS} points", Toast.LENGTH_SHORT).show()
                updateCompletedBadge()
            }
        } else {
            styleOption(button, R.color.wrong, R.color.wrong_light)
            button.isEnabled = false
            showFeedback("❌ Not quite — try again! Hint: read the key points above.", R.color.wrong_light)
        }
    }

    private fun showFeedback(message: String, backgroundColorRes: Int) {
        binding.textFeedback.text = message
        binding.textFeedback.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, backgroundColorRes))
        binding.textFeedback.visibility = View.VISIBLE
    }

    private fun styleOption(button: MaterialButton, strokeColorRes: Int, fillColorRes: Int) {
        button.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(this, strokeColorRes))
        button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, fillColorRes))
        button.setTextColor(ContextCompat.getColor(this, R.color.text_primary))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
