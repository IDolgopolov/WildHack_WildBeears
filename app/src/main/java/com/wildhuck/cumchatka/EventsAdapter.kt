package com.wildhuck.cumchatka

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wildhuck.cumchatka.databinding.EventListItemBinding
import com.wildhuck.cumchatka.databinding.TimelieItemBinding

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsHolder>() {
    private var items = mutableListOf<String>()

    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun insert(data: List<String>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.event_list_item,
            parent,
            false
        )
        context = parent.context
        return EventsHolder(view)
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class EventsHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: EventListItemBinding = EventListItemBinding.bind(view)

        fun bind(data: String) {
            binding.apply {
            }
        }
    }
}