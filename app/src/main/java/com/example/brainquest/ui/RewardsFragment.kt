package com.example.brainquest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brainquest.R
import com.example.brainquest.adapter.BadgeAdapter
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.DialogCertificateBinding
import com.example.brainquest.databinding.FragmentRewardsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!

    private lateinit var progress: ProgressManager
    private lateinit var badgeAdapter: BadgeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressManager(requireContext())

        badgeAdapter = BadgeAdapter(progress.badges()) { badge ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("${badge.emoji}  ${badge.title}")
                .setMessage(
                    (if (badge.unlocked) "Unlocked! 🎉\n\n" else "🔒 Locked\n\nHow to unlock: ") + badge.description
                )
                .setPositiveButton(R.string.close, null)
                .show()
        }
        binding.recyclerBadges.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerBadges.adapter = badgeAdapter

        binding.buttonCertificate.setOnClickListener { showCertificate() }
    }

    override fun onResume() {
        super.onResume()
        val badges = progress.badges()
        badgeAdapter.submit(badges)

        with(binding) {
            textLevelNumber.text = progress.level.toString()
            textLevelTitle.text = "Level ${progress.level} • ${progress.levelTitle}"
            textLevelDetail.text = "${progress.totalPoints} points • ⭐ ${progress.totalStars} / ${LearningRepository.subjects.size * 3} stars"
            progressLevel.max = ProgressManager.POINTS_PER_LEVEL
            progressLevel.progress = progress.pointsInLevel
            textNextLevel.text =
                "${ProgressManager.POINTS_PER_LEVEL - progress.pointsInLevel} points to Level ${progress.level + 1}"
            textBadgeCount.text = "${badges.count { it.unlocked }} / ${badges.size}"

            val unlocked = progress.hasCertificate()
            textCertificateStatus.text = if (unlocked) "🎓 Unlocked!" else "🔒 Locked"
            textCertificateMsg.setText(if (unlocked) R.string.certificate_unlocked else R.string.certificate_locked)
            textCertificateIcon.alpha = if (unlocked) 1f else 0.4f
            buttonCertificate.visibility = if (unlocked) View.VISIBLE else View.GONE
        }
    }

    private fun showCertificate() {
        val certificate = DialogCertificateBinding.inflate(layoutInflater)
        val date = SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(Date())
        certificate.textCertName.text = progress.name
        certificate.textCertSchool.text = "${progress.form} • ${progress.school}"
        certificate.textCertDetails.text =
            "Level ${progress.level} ${progress.levelTitle} • ${progress.totalPoints} points • ★ ${progress.totalStars}"
        certificate.textCertDate.text = "Awarded on $date"

        MaterialAlertDialogBuilder(requireContext())
            .setView(certificate.root)
            .setNegativeButton(R.string.close, null)
            .setPositiveButton(R.string.share_certificate) { _, _ ->
                // Implicit intent
                requireContext().shareText(
                    "BrainQuest Certificate",
                    "🎓 ${progress.name} has earned the BrainQuest Certificate of Achievement for mastering all " +
                            "${LearningRepository.subjects.size} Cambridge IGCSE subjects! (${progress.totalPoints} points, awarded $date)"
                )
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
