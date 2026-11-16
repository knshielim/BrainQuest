package com.example.brainquest.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.adapter.LessonAdapter
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.Lesson
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.data.Subject
import com.example.brainquest.databinding.ActivityModuleBinding

// Learning module
class ModuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModuleBinding
    private lateinit var progress: ProgressManager
    private lateinit var subject: Subject
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Lesson>
    private lateinit var lessonAdapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerLessons)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<Lesson>()
        getData()

        binding.buttonStartQuiz.setOnClickListener {
            // Explicit intent
            val explicitIntent = Intent(this, QuizActivity::class.java)
            explicitIntent.putExtra(Extras.SUBJECT_ID, subject.id)
            startActivity(explicitIntent)
        }
    }

    private fun getData() {
        for (i in subject.lessons.indices) {
            dataList.add(subject.lessons[i])
        }
        lessonAdapter = LessonAdapter(dataList, subject.colorRes, progress.completedLessons()) { lesson ->
            // Explicit intent
            val explicitIntent = Intent(this, LessonActivity::class.java)
            explicitIntent.putExtra(Extras.LESSON_ID, lesson.id)
            startActivity(explicitIntent)
        }
        recyclerView.adapter = lessonAdapter
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
