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
import com.example.brainquest.data.Badge
import com.google.android.material.card.MaterialCardView

class BadgeAdapter(
    private val dataList: ArrayList<Badge>,
    private val onBadgeClick: (Badge) -> Unit
) : RecyclerView.Adapter<BadgeAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        val context = holder.itemView.context

        holder.rvEmoji.text = currentItem.emoji
        holder.rvTitle.text = currentItem.title
        holder.rvEmojiBg.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, if (currentItem.unlocked) R.color.accent_light else R.color.divider)
        )
        holder.rvEmoji.alpha = if (currentItem.unlocked) 1f else 0.35f
        holder.rvTitle.alpha = if (currentItem.unlocked) 1f else 0.5f
        holder.rvLock.visibility = if (currentItem.unlocked) View.GONE else View.VISIBLE
        holder.rvCard.setOnClickListener { onBadgeClick(currentItem) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvCard: MaterialCardView = itemView.findViewById(R.id.cardBadge)
        val rvEmojiBg: FrameLayout = itemView.findViewById(R.id.emojiBg)
        val rvEmoji: TextView = itemView.findViewById(R.id.textEmoji)
        val rvLock: ImageView = itemView.findViewById(R.id.imageLock)
        val rvTitle: TextView = itemView.findViewById(R.id.textTitle)
    }
}
