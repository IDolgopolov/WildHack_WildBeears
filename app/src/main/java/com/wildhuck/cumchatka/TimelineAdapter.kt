package com.wildhuck.cumchatka

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wildhuck.cumchatka.databinding.TimelieItemBinding

class TimelineAdapter(
    private val block: (data: Day) -> Unit
) : RecyclerView.Adapter<TimelineAdapter.TimelineHolder>() {
    private var items = mutableListOf<Day>()

    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun insert(data: List<Day>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.timelie_item,
            parent,
            false
        )
        context = parent.context
        return TimelineHolder(view, block)
    }

    override fun onBindViewHolder(holder: TimelineHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class TimelineHolder(
        view: View,
        private val block: (data: Day) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding: TimelieItemBinding = TimelieItemBinding.bind(view)

        fun bind(data: Day) {
            binding.apply {
                root.setOnClickListener { block(data) }
                timelineYear.text = data.date
                count.text = "Событий: ${data.events.size}"
            }
        }
    }
}