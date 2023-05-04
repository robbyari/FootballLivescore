package com.robbyari.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robbyari.core.R
import com.robbyari.core.databinding.ItemRowMatchTodayBinding
import com.robbyari.core.domain.model.Match

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.ListViewHolder>() {

    private var listData = ArrayList<Match>()
    var onItemClick: ((Match) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Match>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowMatchTodayBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(data: Match) {
            with(binding) {
                tvLiga.text = data.leagueName
                tvDate.text = data.matchDate
                tvTime.text = "${data.matchTime} WIB"
                tvHome.text = data.matchHometeamName
                tvAway.text = data.matchAwayteamName
                tvStadium.text = data.matchStadium
                Glide.with(itemView.context)
                    .load(data.teamHomeBadge)
                    .into(imgHome)
                Glide.with(itemView.context)
                    .load(data.teamAwayBadge)
                    .into(imgAway)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_match_today, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}