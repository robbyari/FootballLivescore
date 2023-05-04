package com.robbyari.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robbyari.core.R
import com.robbyari.core.databinding.ItemRowMatchBinding
import com.robbyari.core.domain.model.Match

class LastMatchAdapter : RecyclerView.Adapter<LastMatchAdapter.ListViewHolder>() {

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
        private val binding = ItemRowMatchBinding.bind(itemView)
        fun bind(data: Match) {
            with(binding) {
                tvLiga.text = data.leagueName
                tvHome.text = data.matchHometeamName
                tvAway.text = data.matchAwayteamName
                tvStadium.text = data.matchStadium
                tvScoreHome.text = data.matchHometeamScore
                tvScoreAway.text = data.matchAwayteamScore
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
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_match, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}