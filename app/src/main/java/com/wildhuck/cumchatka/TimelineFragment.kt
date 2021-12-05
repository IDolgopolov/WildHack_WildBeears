package com.wildhuck.cumchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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
        val timeList = mutableListOf<String>()
        timeList.add("1927")
        timeList.add("1935")
        timeList.add("1950")
        timeList.add("1967")
        timeList.add("1999")
        timeList.add("2001")
        timeList.add("2005")
        binding.apply {
            timelineAdapter = TimelineAdapter { onTimeClick(it) }
            timelineList.adapter = timelineAdapter
            timelineAdapter.insert(timeList)
        }
    }

    private fun onTimeClick(it: String) {
        findNavController().navigate(TimelineFragmentDirections.actionTimelineFragmentToEventsFragment())
    }
}