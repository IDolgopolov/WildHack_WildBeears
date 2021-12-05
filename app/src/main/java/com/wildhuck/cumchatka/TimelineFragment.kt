package com.wildhuck.cumchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wildhuck.cumchatka.databinding.FragmentTimelineBinding

class TimelineFragment : Fragment() {
    private lateinit var binding: FragmentTimelineBinding
    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timeList = mutableListOf<Day>()
        timeList.addAll(Source.getDays())

        binding.apply {
            timelineAdapter = TimelineAdapter { onTimeClick(it) }
            timelineList.adapter = timelineAdapter
            timelineAdapter.insert(timeList)
        }

    }

    private fun onTimeClick(it: Day) {
        EventsFragment.day = it
        findNavController().navigate(TimelineFragmentDirections.actionTimelineFragmentToEventsFragment())
    }
}