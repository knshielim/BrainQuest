package com.example.brainquest.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.data.Subject
import com.example.brainquest.databinding.ItemSubjectBinding
import com.example.brainquest.ui.starText

class SubjectAdapter(
    private val subjects: List<Subject>,
    private val progress: ProgressManager,
    private val onSubjectClick: (Subject) -> Unit
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(val binding: ItemSubjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjects[position]
        val context = holder.itemView.context
        val color = ContextCompat.getColor(context, subject.colorRes)
        val done = progress.completedLessonCount(subject)
        val best = progress.bestScore(subject.id)

        with(holder.binding) {
            iconBg.backgroundTintList = ColorStateList.valueOf(color)
            imageIcon.setImageResource(subject.iconRes)
            textName.text = subject.name
            textDescription.text = subject.description
            progressLessons.max = subject.lessons.size
            progressLessons.progress = done
            progressLessons.setIndicatorColor(color)
            progressLessons.trackColor = ContextCompat.getColor(context, subject.lightColorRes)
            textLessons.text = "$done/${subject.lessons.size} lessons"
            textBest.text = if (best < 0) "Not attempted" else "Best $best% ${starText(progress.bestStars(subject.id))}"
            cardSubject.setOnClickListener { onSubjectClick(subject) }
        }
    }

    override fun getItemCount(): Int = subjects.size
}
