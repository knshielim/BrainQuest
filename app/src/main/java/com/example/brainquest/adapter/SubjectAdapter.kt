package com.example.brainquest.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.data.Subject
import com.example.brainquest.ui.starText
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.LinearProgressIndicator

class SubjectAdapter(
    private val dataList: ArrayList<Subject>,
    private val progress: ProgressManager,
    private val onSubjectClick: (Subject) -> Unit
) : RecyclerView.Adapter<SubjectAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        val context = holder.itemView.context
        val color = ContextCompat.getColor(context, currentItem.colorRes)
        val done = progress.completedLessonCount(currentItem)
        val best = progress.bestScore(currentItem.id)

        holder.rvIconBg.backgroundTintList = ColorStateList.valueOf(color)
        holder.rvIcon.setImageResource(currentItem.iconRes)
        holder.rvName.text = currentItem.name
        holder.rvDescription.text = currentItem.description
        holder.rvProgress.max = currentItem.lessons.size
        holder.rvProgress.progress = done
        holder.rvProgress.setIndicatorColor(color)
        holder.rvProgress.trackColor = ContextCompat.getColor(context, currentItem.lightColorRes)
        holder.rvLessons.text = "$done/${currentItem.lessons.size} lessons"
        holder.rvBest.text = if (best < 0) "Not attempted" else "Best $best% ${starText(progress.bestStars(currentItem.id))}"
        holder.rvCard.setOnClickListener { onSubjectClick(currentItem) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvCard: MaterialCardView = itemView.findViewById(R.id.cardSubject)
        val rvIconBg: FrameLayout = itemView.findViewById(R.id.iconBg)
        val rvIcon: ImageView = itemView.findViewById(R.id.imageIcon)
        val rvName: TextView = itemView.findViewById(R.id.textName)
        val rvDescription: TextView = itemView.findViewById(R.id.textDescription)
        val rvProgress: LinearProgressIndicator = itemView.findViewById(R.id.progressLessons)
        val rvLessons: TextView = itemView.findViewById(R.id.textLessons)
        val rvBest: TextView = itemView.findViewById(R.id.textBest)
    }
}
