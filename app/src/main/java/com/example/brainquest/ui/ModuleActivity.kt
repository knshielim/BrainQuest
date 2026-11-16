package com.example.brainquest.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainquest.adapter.LessonAdapter
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.data.Subject
import com.example.brainquest.databinding.ActivityModuleBinding

class ModuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModuleBinding
    private lateinit var progress: ProgressManager
    private lateinit var subject: Subject
    private lateinit var lessonAdapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivityModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main, binding.appBarMain.root)

        progress = ProgressManager(this)
        subject = LearningRepository.subject(intent.getStringExtra(Extras.SUBJECT_ID))

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = subject.name
        supportActionBar?.subtitle = "Learning Module"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val color = ContextCompat.getColor(this, subject.colorRes)
        binding.cardHeader.setCardBackgroundColor(color)
        binding.imageIcon.setImageResource(subject.iconRes)
        binding.textSubjectName.text = subject.name
        binding.textSubjectDesc.text = subject.description
        binding.buttonStartQuiz.backgroundTintList = ColorStateList.valueOf(color)

        lessonAdapter = LessonAdapter(subject.lessons, subject.colorRes, progress.completedLessons()) { lesson ->
            // Explicit intent: open the lesson
            val intent = Intent(this, LessonActivity::class.java)
            intent.putExtra(Extras.LESSON_ID, lesson.id)
            startActivity(intent)
        }
        binding.recyclerLessons.layoutManager = LinearLayoutManager(this)
        binding.recyclerLessons.adapter = lessonAdapter

        binding.buttonStartQuiz.setOnClickListener {
            // Explicit intent: start the quiz game for this subject
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra(Extras.SUBJECT_ID, subject.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val done = progress.completedLessonCount(subject)
        val best = progress.bestScore(subject.id)
        binding.progressModule.max = subject.lessons.size
        binding.progressModule.progress = done
        binding.textModuleProgress.text = "$done / ${subject.lessons.size} lessons completed"
        binding.textModuleBest.text =
            if (best < 0) "Quiz not attempted" else "Best $best% ${starText(progress.bestStars(subject.id))}"
        lessonAdapter.updateCompleted(progress.completedLessons())
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
