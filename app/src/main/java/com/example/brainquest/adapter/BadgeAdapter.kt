package com.example.brainquest.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brainquest.R
import com.example.brainquest.data.Badge
import com.google.android.material.card.MaterialCardView

// Badge adapter
class BadgeAdapter(
    private val dataList: ArrayList<Badge>,
    private val onBadgeClick: (Badge) -> Unit
) : RecyclerView.Adapter<BadgeAdapter.ViewHolderClass>() {

    // Grey filter for locked badges
    private val greyFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]

        holder.rvImage.setImageResource(currentItem.imageRes)
        holder.rvTitle.text = currentItem.title
        // Locked badges are faded and grey
        holder.rvImage.alpha = if (currentItem.unlocked) 1f else 0.35f
        holder.rvImage.colorFilter = if (currentItem.unlocked) null else greyFilter
        holder.rvTitle.alpha = if (currentItem.unlocked) 1f else 0.5f
        holder.rvLock.visibility = if (currentItem.unlocked) View.GONE else View.VISIBLE
        holder.rvCard.setOnClickListener { onBadgeClick(currentItem) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvCard: MaterialCardView = itemView.findViewById(R.id.cardBadge)
        val rvImage: ImageView = itemView.findViewById(R.id.imageBadge)
        val rvLock: ImageView = itemView.findViewById(R.id.imageLock)
        val rvTitle: TextView = itemView.findViewById(R.id.textTitle)
    }
}
