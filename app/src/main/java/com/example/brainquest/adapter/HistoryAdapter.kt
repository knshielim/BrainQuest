package com.example.brainquest.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.data.LearningRepository
import com.example.brainquest.data.QuizRecord
import com.example.brainquest.databinding.ItemHistoryBinding
import com.example.brainquest.ui.starText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter(private var records: List<QuizRecord>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, h:mm a", Locale.getDefault())

    class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val record = records[position]
        val subject = LearningRepository.subject(record.subjectId)
        val context = holder.itemView.context
        with(holder.binding) {
            iconBg.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, subject.colorRes))
            imageIcon.setImageResource(subject.iconRes)
            textSubject.text = "${subject.name} Quiz"
            textDate.text = dateFormat.format(Date(record.timestamp))
            textPercent.text = "${record.percent}%"
            textPercent.setTextColor(
                ContextCompat.getColor(context, if (record.percent >= 50) R.color.correct else R.color.wrong)
            )
            textReward.text = "${starText(record.stars)}  +${record.points} pts"
        }
    }

    override fun getItemCount(): Int = records.size

    fun submit(newRecords: List<QuizRecord>) {
        val oldSize = records.size
        records = newRecords
        if (oldSize > 0) notifyItemRangeRemoved(0, oldSize)
        notifyItemRangeInserted(0, records.size)
    }
}
