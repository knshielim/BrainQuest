package com.example.brainquest.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.adapter.BadgeAdapter
import com.example.brainquest.data.Badge
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.DialogCertificateBinding
import com.example.brainquest.databinding.FragmentRewardsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Rewards tab (fragment)
class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!

    private lateinit var progress: ProgressManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Badge>
    private lateinit var badgeAdapter: BadgeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressManager(requireContext())

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerBadges)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<Badge>()
        badgeAdapter = BadgeAdapter(dataList) { badge ->
            MaterialAlertDialogBuilder(requireContext())
                .setIcon(badge.imageRes)
                .setTitle(badge.title)
                .setMessage(
                    (if (badge.unlocked) "Unlocked! 🎉\n\n" else "🔒 Locked\n\nHow to unlock: ") + badge.description
                )
                .setPositiveButton(R.string.close, null)
                .show()
        }
        recyclerView.adapter = badgeAdapter

        binding.buttonCertificate.setOnClickListener { showCertificate() }
    }

    override fun onResume() {
        super.onResume()
        getData()
        val badges = dataList

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

    private fun getData() {
        dataList.clear()
        val badges = progress.badges()
        for (i in badges.indices) {
            dataList.add(badges[i])
        }
        badgeAdapter.notifyDataSetChanged()
    }

    // Certificate
    private fun showCertificate() {
        val certificateView = makeCertificate()

        // Preview image of the A4 certificate
        val preview = ImageView(requireContext())
        preview.adjustViewBounds = true
        preview.setImageBitmap(certificateBitmap(certificateView))
        val padding = (16 * resources.displayMetrics.density).toInt()
        preview.setPadding(padding, padding, padding, 0)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.certificate)
            .setView(preview)
            .setNeutralButton(R.string.close, null)
            .setNegativeButton(R.string.share_certificate) { _, _ ->
                // Implicit intent
                requireContext().shareText(
                    "BrainQuest Certificate",
                    "🎓 ${progress.name} has earned the BrainQuest Certificate of Achievement for mastering all " +
                            "${LearningRepository.subjects.size} Cambridge IGCSE subjects! (${progress.totalPoints} points)"
                )
            }
            .setPositiveButton(R.string.download_pdf) { _, _ ->
                // Choose where to save the PDF
                savePdf.launch("BrainQuest_Certificate_${progress.name.replace(" ", "_")}.pdf")
            }
            .show()
    }

    // Certificate layout
    private fun makeCertificate(): View {
        val certificate = DialogCertificateBinding.inflate(layoutInflater)
        val date = SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(Date())
        certificate.textCertName.text = progress.name
        certificate.textCertSchool.text = "${progress.form} • ${progress.school}"
        certificate.textCertDetails.text =
            "Level ${progress.level} ${progress.levelTitle} • ${progress.totalPoints} points • ★ ${progress.totalStars}"
        certificate.textCertDate.text = date

        val density = resources.displayMetrics.density
        val width = (A4_WIDTH * density).toInt()
        val height = (A4_HEIGHT * density).toInt()
        certificate.root.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )
        certificate.root.layout(0, 0, width, height)
        return certificate.root
    }

    // Preview picture
    private fun certificateBitmap(certificateView: View): Bitmap {
        val scale = 1.5f
        val bitmap = Bitmap.createBitmap((A4_WIDTH * scale).toInt(), (A4_HEIGHT * scale).toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.scale(scale / resources.displayMetrics.density, scale / resources.displayMetrics.density)
        certificateView.draw(canvas)
        return bitmap
    }

    // Save as PDF
    private val savePdf = registerForActivityResult(ActivityResultContracts.CreateDocument("application/pdf")) { uri ->
        if (uri != null) writePdf(uri)
    }

    private fun writePdf(uri: Uri) {
        val certificateView = makeCertificate()
        val pdf = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(A4_WIDTH, A4_HEIGHT, 1).create()
        val page = pdf.startPage(pageInfo)
        page.canvas.scale(1f / resources.displayMetrics.density, 1f / resources.displayMetrics.density)
        certificateView.draw(page.canvas)
        pdf.finishPage(page)
        try {
            requireContext().contentResolver.openOutputStream(uri)?.use { pdf.writeTo(it) }
            Toast.makeText(requireContext(), R.string.certificate_saved, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), R.string.certificate_save_failed, Toast.LENGTH_SHORT).show()
        } finally {
            pdf.close()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val A4_WIDTH = 842
        private const val A4_HEIGHT = 595
    }
}
