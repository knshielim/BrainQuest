package com.example.brainquest.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.brainquest.R
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.Question
import com.example.brainquest.data.Subject
import com.example.brainquest.databinding.ActivityQuizBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var subject: Subject
    private lateinit var questions: List<Question>

    private var currentIndex = 0
    private var correctCount = 0
    private var points = 0
    private var streak = 0
    private var bestStreak = 0
    private var answered = false
    private var secondsLeft = SECONDS_PER_QUESTION
    private var correctPosition = 0
    private var timer: CountDownTimer? = null
    private val optionButtons = mutableListOf<MaterialButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main, binding.appBarMain.root)

        subject = LearningRepository.subject(intent.getStringExtra(Extras.SUBJECT_ID))
        questions = subject.questions.shuffled().take(QUESTIONS_PER_QUIZ)

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = "${subject.name} Quiz"
        supportActionBar?.subtitle = "Answer fast for bonus points!"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val color = ContextCompat.getColor(this, subject.colorRes)
        binding.progressQuiz.max = questions.size
        binding.progressQuiz.setIndicatorColor(color)
        binding.progressQuiz.trackColor = ContextCompat.getColor(this, subject.lightColorRes)
        binding.buttonNext.backgroundTintList = ColorStateList.valueOf(color)

        binding.buttonNext.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                showQuestion()
            } else {
                finishQuiz()
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = confirmQuit()
        })

        updateScorePills()
        showQuestion()
    }

    private fun showQuestion() {
        answered = false
        val question = questions[currentIndex]

        binding.textQuestionCount.text = "Question ${currentIndex + 1} of ${questions.size}"
        binding.progressQuiz.setProgressCompat(currentIndex + 1, true)
        binding.textQuestion.text = question.text
        binding.cardFeedback.visibility = View.GONE
        binding.buttonNext.visibility = View.INVISIBLE

        val order = question.options.indices.shuffled()
        correctPosition = order.indexOf(question.answerIndex)

        binding.containerOptions.removeAllViews()
        optionButtons.clear()
        order.forEachIndexed { position, optionIndex ->
            val button = layoutInflater.inflate(R.layout.item_option_button, binding.containerOptions, false) as MaterialButton
            button.text = "${'A' + position}.  ${question.options[optionIndex]}"
            button.setOnClickListener { onAnswerSelected(position) }
            binding.containerOptions.addView(button)
            optionButtons += button
        }

        startTimer()
    }

    private fun startTimer() {
        timer?.cancel()
        secondsLeft = SECONDS_PER_QUESTION
        updateTimerText()
        timer = object : CountDownTimer(SECONDS_PER_QUESTION * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                secondsLeft = ((millisUntilFinished + 999) / 1000).toInt()
                updateTimerText()
            }

            override fun onFinish() {
                secondsLeft = 0
                updateTimerText()
                onTimeUp()
            }
        }.start()
    }

    private fun updateTimerText() {
        binding.textTimer.text = "⏱ ${secondsLeft}s"
        binding.textTimer.setTextColor(
            ContextCompat.getColor(this, if (secondsLeft <= 5) R.color.wrong else R.color.primary)
        )
    }

    private fun onAnswerSelected(position: Int) {
        if (answered) return
        answered = true
        timer?.cancel()
        lockOptions()

        val question = questions[currentIndex]
        if (position == correctPosition) {
            correctCount++
            streak++
            bestStreak = maxOf(bestStreak, streak)
            val streakBonus = if (streak >= 3) STREAK_BONUS else 0
            val gained = BASE_POINTS + secondsLeft / 2 + streakBonus
            points += gained
            styleOption(optionButtons[position], R.color.correct, R.color.correct_light)
            val title = "✅ Correct! +$gained points" + if (streakBonus > 0) "  🔥 streak bonus" else ""
            showFeedback(title, question.explanation, correct = true)
        } else {
            streak = 0
            styleOption(optionButtons[position], R.color.wrong, R.color.wrong_light)
            styleOption(optionButtons[correctPosition], R.color.correct, R.color.correct_light)
            showFeedback("❌ Not quite!", question.explanation, correct = false)
        }
        afterAnswer()
    }

    private fun onTimeUp() {
        if (answered) return
        answered = true
        streak = 0
        lockOptions()
        styleOption(optionButtons[correctPosition], R.color.correct, R.color.correct_light)
        showFeedback(getString(R.string.times_up), questions[currentIndex].explanation, correct = false)
        afterAnswer()
    }

    private fun afterAnswer() {
        updateScorePills()
        val last = currentIndex == questions.size - 1
        binding.buttonNext.setText(if (last) R.string.finish_quiz else R.string.next_question)
        binding.buttonNext.visibility = View.VISIBLE
    }

    private fun lockOptions() {
        optionButtons.forEach { it.isClickable = false }
    }

    private fun updateScorePills() {
        binding.textScore.text = "⚡ $points"
        binding.textStreak.text = "🔥 $streak"
        binding.textStreak.visibility = if (streak >= 2) View.VISIBLE else View.GONE
    }

    private fun showFeedback(title: String, body: String, correct: Boolean) {
        binding.cardFeedback.setCardBackgroundColor(
            ContextCompat.getColor(this, if (correct) R.color.correct_light else R.color.wrong_light)
        )
        binding.textFeedbackTitle.setTextColor(
            ContextCompat.getColor(this, if (correct) R.color.correct else R.color.wrong)
        )
        binding.textFeedbackTitle.text = title
        binding.textFeedbackBody.text = body
        binding.cardFeedback.visibility = View.VISIBLE
    }

    private fun styleOption(button: MaterialButton, strokeColorRes: Int, fillColorRes: Int) {
        button.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(this, strokeColorRes))
        button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, fillColorRes))
    }

    private fun finishQuiz() {
        timer?.cancel()
        // Explicit intent
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(Extras.SUBJECT_ID, subject.id)
            putExtra(Extras.CORRECT, correctCount)
            putExtra(Extras.TOTAL, questions.size)
            putExtra(Extras.POINTS, points)
            putExtra(Extras.BEST_STREAK, bestStreak)
        }
        startActivity(intent)
        finish()
    }

    private fun confirmQuit() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.quit_quiz_title)
            .setMessage(R.string.quit_quiz_msg)
            .setNegativeButton(R.string.keep_playing, null)
            .setPositiveButton(R.string.quit) { _, _ -> finish() }
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        confirmQuit()
        return true
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

    companion object {
        private const val QUESTIONS_PER_QUIZ = 10
        private const val SECONDS_PER_QUESTION = 45
        private const val BASE_POINTS = 10
        private const val STREAK_BONUS = 5
    }
}
