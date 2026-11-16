package com.example.brainquest.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.data.Lesson
import com.google.android.material.card.MaterialCardView

class LessonAdapter(
    private val dataList: ArrayList<Lesson>,
    private val subjectColorRes: Int,
    private var completedIds: Set<String>,
    private val onLessonClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        val context = holder.itemView.context
        val done = currentItem.id in completedIds

        holder.rvImage.setImageResource(currentItem.imageRes)
        holder.rvNumber.text = "LESSON ${position + 1}"
        holder.rvNumber.setTextColor(ContextCompat.getColor(context, subjectColorRes))
        holder.rvTitle.text = currentItem.title
        holder.rvSummary.text = currentItem.summary
        holder.rvStatus.setImageResource(if (done) R.drawable.ic_check_circle else R.drawable.ic_play_circle)
        holder.rvStatus.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, if (done) R.color.correct else subjectColorRes)
        )
        holder.rvCard.setOnClickListener { onLessonClick(currentItem) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateCompleted(ids: Set<String>) {
        completedIds = ids
        notifyItemRangeChanged(0, dataList.size)
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvCard: MaterialCardView = itemView.findViewById(R.id.cardLesson)
        val rvImage: ImageView = itemView.findViewById(R.id.imageThumb)
        val rvNumber: TextView = itemView.findViewById(R.id.textLessonNumber)
        val rvTitle: TextView = itemView.findViewById(R.id.textTitle)
        val rvSummary: TextView = itemView.findViewById(R.id.textSummary)
        val rvStatus: ImageView = itemView.findViewById(R.id.imageStatus)
    }
}
