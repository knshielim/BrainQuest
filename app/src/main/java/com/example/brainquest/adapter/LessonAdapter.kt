package com.example.brainquest.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.data.Lesson
import com.example.brainquest.databinding.ItemLessonBinding

class LessonAdapter(
    private val lessons: List<Lesson>,
    private val subjectColorRes: Int,
    private var completedIds: Set<String>,
    private val onLessonClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    class LessonViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        val context = holder.itemView.context
        val done = lesson.id in completedIds

        with(holder.binding) {
            imageThumb.setImageResource(lesson.imageRes)
            textLessonNumber.text = "LESSON ${position + 1}"
            textLessonNumber.setTextColor(ContextCompat.getColor(context, subjectColorRes))
            textTitle.text = lesson.title
            textSummary.text = lesson.summary
            imageStatus.setImageResource(if (done) R.drawable.ic_check_circle else R.drawable.ic_play_circle)
            imageStatus.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(context, if (done) R.color.correct else subjectColorRes)
            )
            cardLesson.setOnClickListener { onLessonClick(lesson) }
        }
    }

    override fun getItemCount(): Int = lessons.size

    fun updateCompleted(ids: Set<String>) {
        completedIds = ids
        notifyItemRangeChanged(0, lessons.size)
    }
}
