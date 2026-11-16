package com.example.brainquest.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.brainquest.R
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.Lesson
import com.example.brainquest.databinding.ActivityFlashcardBinding
import kotlin.math.abs

class FlashcardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlashcardBinding
    private lateinit var lesson: Lesson

    private data class Card(val title: String?, val body: String)

    private var cards: List<Card> = emptyList()
    private var currentCard = 0
    private var subjectColor = 0
    private var subjectLightColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main, binding.appBarMain.root)

        val found = LearningRepository.lesson(intent.getStringExtra(Extras.LESSON_ID))
        if (found == null) {
            finish()
            return
        }
        lesson = found
        val subject = LearningRepository.subject(lesson.subjectId)
        subjectColor = ContextCompat.getColor(this, subject.colorRes)
        subjectLightColor = ContextCompat.getColor(this, subject.lightColorRes)

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = getString(R.string.flashcards)
        supportActionBar?.subtitle = subject.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.textLessonTitle.text = lesson.title
        binding.textCardTitle.setTextColor(subjectColor)
        binding.viewAccent.setBackgroundColor(subjectColor)
        binding.buttonAttemptQuiz.backgroundTintList = ColorStateList.valueOf(subjectColor)
        binding.buttonBack.setTextColor(subjectColor)
        binding.buttonBack.strokeColor = ColorStateList.valueOf(subjectColor)

        cards = makeCards(lesson.content)
        setUpSwipe()

        // Explicit intent
        binding.buttonAttemptQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra(Extras.SUBJECT_ID, lesson.subjectId)
            startActivity(intent)
        }
        binding.buttonBack.setOnClickListener { finish() }

        showCard(0)
    }

    private fun makeCards(content: String): List<Card> =
        content.split("\n\n").map { it.trim() }.filter { it.isNotEmpty() }.map { paragraph ->
            val firstLine = paragraph.substringBefore("\n")
            val colon = firstLine.indexOf(':')
            if (colon in 1..45) {
                val body = (firstLine.substring(colon + 1).trim() + "\n" + paragraph.substringAfter("\n", "")).trim()
                Card(firstLine.substring(0, colon), body)
            } else {
                Card(null, paragraph)
            }
        }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpSwipe() {
        val detector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean = true
            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                val dx = e2.x - (e1?.x ?: e2.x)
                if (abs(dx) < dp(60) || abs(velocityX) < abs(velocityY)) return false
                showCard(currentCard + if (dx < 0) 1 else -1)
                return true
            }
        })
        binding.cardFlash.setOnTouchListener { _, event -> detector.onTouchEvent(event) }
    }

    private fun showCard(index: Int) {
        if (cards.isEmpty() || index !in cards.indices) return
        val direction = if (index >= currentCard) 1 else -1
        currentCard = index
        val card = cards[index]

        binding.textCounter.text = getString(R.string.card_counter, index + 1, cards.size)
        binding.textCardTitle.text = card.title.orEmpty()
        binding.textCardTitle.visibility = if (card.title == null) View.GONE else View.VISIBLE
        binding.textCardBody.text = card.body
        binding.textCardBody.visibility = if (card.body.isEmpty()) View.GONE else View.VISIBLE

        binding.cardFlash.alpha = 0f
        binding.cardFlash.translationX = direction * dp(40).toFloat()
        binding.cardFlash.animate().alpha(1f).translationX(0f).setDuration(220).start()

        buildPager(binding.pager)
    }

    private fun buildPager(container: LinearLayout) {
        container.removeAllViews()
        container.addView(pagerButton("‹", enabled = currentCard > 0, selected = false) { showCard(currentCard - 1) })
        pageNumbers(cards.size, currentCard).forEach { page ->
            if (page == null) {
                container.addView(pagerButton("…", enabled = false, selected = false, plain = true) {})
            } else {
                container.addView(pagerButton("${page + 1}", enabled = true, selected = page == currentCard) { showCard(page) })
            }
        }
        container.addView(pagerButton("›", enabled = currentCard < cards.size - 1, selected = false) { showCard(currentCard + 1) })
    }

    private fun pageNumbers(total: Int, current: Int): List<Int?> {
        if (total <= 8) return (0 until total).toList()
        val shown = (listOf(0, total - 1) + (current - 1..current + 1)).filter { it in 0 until total }.toSortedSet()
        val result = mutableListOf<Int?>()
        var previous = -1
        shown.forEach { page ->
            if (previous >= 0 && page - previous > 1) result += null
            result += page
            previous = page
        }
        return result
    }

    private fun pagerButton(label: String, enabled: Boolean, selected: Boolean, plain: Boolean = false, onClick: () -> Unit): TextView {
        val size = dp(34)
        return TextView(this).apply {
            text = label
            gravity = Gravity.CENTER
            textSize = if (label == "‹" || label == "›") 20f else 14f
            setTypeface(typeface, if (selected) Typeface.BOLD else Typeface.NORMAL)
            layoutParams = LinearLayout.LayoutParams(size, size).apply { marginStart = dp(3); marginEnd = dp(3) }
            if (!plain) {
                setBackgroundResource(R.drawable.bg_circle)
                backgroundTintList = ColorStateList.valueOf(if (selected) subjectColor else subjectLightColor)
            }
            setTextColor(if (selected) ContextCompat.getColor(context, R.color.white) else subjectColor)
            alpha = if (enabled || plain) 1f else 0.3f
            isEnabled = enabled
            contentDescription = if (label == "‹") "Previous card" else if (label == "›") "Next card" else "Card $label"
            setOnClickListener { onClick() }
        }
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
