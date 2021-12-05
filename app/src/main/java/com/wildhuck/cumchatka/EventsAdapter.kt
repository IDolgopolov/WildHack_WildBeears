package com.wildhuck.cumchatka

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wildhuck.cumchatka.databinding.EventListItemBinding


class EventsAdapter(
    private val block: (data: Event) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventsHolder>() {
    private var items = mutableListOf<Event>()

    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun insert(data: List<Event>) {
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
        return EventsHolder(view, block)
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class EventsHolder(
        view: View,
        private val block: (data: Event) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding: EventListItemBinding = EventListItemBinding.bind(view)

        fun bind(data: Event) {
            binding.apply {
                binding.promotionMediumTvTitle.text = data.title
                binding.promotionMediumTvBusiness.text = data.text
                binding.promotionMediumIvImage.setImageDrawable(data.img)
                binding.promotionMediumTvDuration.text = data.date

                root.setOnClickListener { block(data) }
            }
        }
    }
}

