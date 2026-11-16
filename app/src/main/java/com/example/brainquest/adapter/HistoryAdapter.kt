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
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.QuizRecord
import com.example.brainquest.ui.starText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter(private val dataList: ArrayList<QuizRecord>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolderClass>() {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, h:mm a", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        val subject = LearningRepository.subject(currentItem.subjectId)
        val context = holder.itemView.context

        holder.rvIconBg.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, subject.colorRes))
        holder.rvIcon.setImageResource(subject.iconRes)
        holder.rvSubject.text = "${subject.name} Quiz"
        holder.rvDate.text = dateFormat.format(Date(currentItem.timestamp))
        holder.rvPercent.text = "${currentItem.percent}%"
        holder.rvPercent.setTextColor(
            ContextCompat.getColor(context, if (currentItem.percent >= 50) R.color.correct else R.color.wrong)
        )
        holder.rvReward.text = "${starText(currentItem.stars)}  +${currentItem.points} pts"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvIconBg: FrameLayout = itemView.findViewById(R.id.iconBg)
        val rvIcon: ImageView = itemView.findViewById(R.id.imageIcon)
        val rvSubject: TextView = itemView.findViewById(R.id.textSubject)
        val rvDate: TextView = itemView.findViewById(R.id.textDate)
        val rvPercent: TextView = itemView.findViewById(R.id.textPercent)
        val rvReward: TextView = itemView.findViewById(R.id.textReward)
    }
}
