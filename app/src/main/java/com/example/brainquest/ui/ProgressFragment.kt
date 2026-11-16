package com.example.brainquest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.adapter.HistoryAdapter
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.data.QuizRecord
import com.example.brainquest.databinding.FragmentProgressBinding
import com.example.brainquest.databinding.ItemSubjectProgressBinding

// Progress tab (fragment)
class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private lateinit var progress: ProgressManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<QuizRecord>
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressManager(requireContext())

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerHistory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<QuizRecord>()
        historyAdapter = HistoryAdapter(dataList)
        recyclerView.adapter = historyAdapter
    }

    override fun onResume() {
        super.onResume()
        bindOverview()
        bindSubjects()
        bindAnalysis()
        getData()
        binding.textEmptyHistory.visibility = if (dataList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun getData() {
        dataList.clear()
        val history = progress.history()
        for (i in history.indices) {
            dataList.add(history[i])
        }
        historyAdapter.notifyDataSetChanged()
    }

    private fun bindOverview() {
        binding.textQuizzes.text = progress.quizzesTaken.toString()
        binding.textAverage.text = "${progress.averageScore}%"
        binding.textLessonsDone.text = "${progress.completedLessons().size}/${LearningRepository.totalLessons}"
        binding.textTotalPoints.text = progress.totalPoints.toString()
    }

    private fun bindSubjects() {
        val container = binding.containerSubjects
        container.removeAllViews()
        LearningRepository.subjects.forEach { subject ->
            val row = ItemSubjectProgressBinding.inflate(layoutInflater, container, false)
            val color = ContextCompat.getColor(requireContext(), subject.colorRes)
            val best = progress.bestScore(subject.id)
            row.iconBg.backgroundTintList = ColorStateList.valueOf(color)
            row.imageIcon.setImageResource(subject.iconRes)
            row.textName.text = subject.name
            row.textDetail.text = "${progress.attempts(subject.id)} attempts • " +
                    "${progress.completedLessonCount(subject)}/${subject.lessons.size} lessons"
            row.textScore.text = if (best < 0) "–" else "$best%"
            row.textStars.text = starText(progress.bestStars(subject.id))
            row.progressScore.max = 100
            row.progressScore.progress = best.coerceAtLeast(0)
            row.progressScore.setIndicatorColor(color)
            row.progressScore.trackColor = ContextCompat.getColor(requireContext(), subject.lightColorRes)
            container.addView(row.root)
        }
    }

    // Progress analysis
    private fun bindAnalysis() {
        val attempted = LearningRepository.subjects.filter { progress.bestScore(it.id) >= 0 }
        val notTried = LearningRepository.subjects.filter { progress.bestScore(it.id) < 0 }
        val lines = mutableListOf<String>()

        if (attempted.isEmpty()) {
            lines += "📊 No quiz data yet."
            lines += "👉 Start with any module, read the lessons and take the quiz to see your analysis here."
        } else {
            val strongest = attempted.maxBy { progress.bestScore(it.id) }
            val weakest = attempted.minBy { progress.bestScore(it.id) }
            lines += "💪 Strongest subject: ${strongest.name} (${progress.bestScore(strongest.id)}%)"
            if (weakest != strongest) {
                lines += "📈 Needs more practice: ${weakest.name} (${progress.bestScore(weakest.id)}%)"
            }
            val avg = progress.averageScore
            lines += when {
                avg >= 80 -> "🌟 Excellent! Your average is $avg%. Keep it up and aim for 100%."
                avg >= 60 -> "👍 Good work! Your average is $avg%. Revise the key points to reach 80%."
                else -> "📚 Your average is $avg%. Re-read the lessons and try the practice questions again."
            }
            if (notTried.isNotEmpty()) {
                lines += "🧭 Not tried yet: ${notTried.joinToString { it.name }}"
            }
        }
        val remaining = LearningRepository.totalLessons - progress.completedLessons().size
        if (remaining > 0) lines += "📖 $remaining lesson(s) left to complete."
        binding.textAnalysis.text = lines.joinToString("\n")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
