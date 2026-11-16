package com.example.brainquest.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.data.Badge
import com.example.brainquest.databinding.ItemBadgeBinding

class BadgeAdapter(
    private var badges: List<Badge>,
    private val onBadgeClick: (Badge) -> Unit
) : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    class BadgeViewHolder(val binding: ItemBadgeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val binding = ItemBadgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadgeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        val badge = badges[position]
        val context = holder.itemView.context
        with(holder.binding) {
            textEmoji.text = badge.emoji
            textTitle.text = badge.title
            emojiBg.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(context, if (badge.unlocked) R.color.accent_light else R.color.divider)
            )
            textEmoji.alpha = if (badge.unlocked) 1f else 0.35f
            textTitle.alpha = if (badge.unlocked) 1f else 0.5f
            imageLock.visibility = if (badge.unlocked) View.GONE else View.VISIBLE
            cardBadge.setOnClickListener { onBadgeClick(badge) }
        }
    }

    override fun getItemCount(): Int = badges.size

    fun submit(newBadges: List<Badge>) {
        badges = newBadges
        notifyItemRangeChanged(0, badges.size)
    }
}
