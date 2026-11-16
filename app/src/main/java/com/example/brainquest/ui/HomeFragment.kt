package com.example.brainquest.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.adapter.SubjectAdapter
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.data.Subject
import com.example.brainquest.databinding.FragmentHomeBinding

// Learn tab (fragment)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var progress: ProgressManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Subject>
    private lateinit var subjectAdapter: SubjectAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressManager(requireContext())

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerSubjects)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<Subject>()
        getData()

        binding.textFact.text = LearningRepository.funFacts.random()
    }

    private fun getData() {
        for (i in LearningRepository.subjects.indices) {
            dataList.add(LearningRepository.subjects[i])
        }
        subjectAdapter = SubjectAdapter(dataList, progress) { subject ->
            // Explicit intent
            val explicitIntent = Intent(requireContext(), ModuleActivity::class.java)
            explicitIntent.putExtra(Extras.SUBJECT_ID, subject.id)
            startActivity(explicitIntent)
        }
        recyclerView.adapter = subjectAdapter
    }

    override fun onResume() {
        super.onResume()
        bindHeader()
        subjectAdapter.notifyDataSetChanged()
    }

    private fun bindHeader() {
        with(binding) {
            textAvatar.text = progress.avatar
            textGreeting.text = "Hi, ${progress.firstName}! 👋"
            textInfo.text = "${progress.form} • ${progress.school}"
            textLevel.text = "Lv ${progress.level}"
            textLevelProgress.text =
                "${progress.levelTitle} • ${progress.pointsInLevel} / ${ProgressManager.POINTS_PER_LEVEL} points to Level ${progress.level + 1}"
            progressLevel.max = ProgressManager.POINTS_PER_LEVEL
            progressLevel.progress = progress.pointsInLevel
            textStars.text = "⭐ ${progress.totalStars}"
            textPoints.text = "⚡ ${progress.totalPoints}"
            textBadges.text = "🏅 ${progress.unlockedBadgeIds().size}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
