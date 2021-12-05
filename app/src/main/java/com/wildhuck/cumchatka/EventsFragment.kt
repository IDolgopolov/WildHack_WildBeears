package com.wildhuck.cumchatka

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wildhuck.cumchatka.databinding.FragmentEventsBinding
import com.wildhuck.cumchatka.databinding.FragmentTimelineBinding
import kotlin.math.abs

class EventsFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding
    private var eventsAdapter: EventsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val viewPagerSavedState = sharedPref.getInt("viewPagerSavedState", 0)
        binding.viewPager.currentItem = viewPagerSavedState
    }

    override fun onPause() {
        super.onPause()
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with (sharedPref!!.edit()) {
            putInt("viewPagerSavedState", binding.viewPager.currentItem)
            apply()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsAdapter = EventsAdapter{ onEventClick(it) }
        val events = mutableListOf<String>()
        events.add("event1")
        events.add("event2")
        events.add("event3")
        events.add("event4")
        events.add("event5")
        events.add("event6")
        events.add("event7")
        eventsAdapter?.insert(events)

        binding.apply {
            back.setOnClickListener {
                findNavController().navigateUp()
            }

            viewPager.apply {
                adapter = eventsAdapter
                offscreenPageLimit = 10

                setPageTransformer { page, position ->

                    page.apply {

                        ViewCompat.setElevation(page, 20 - abs(position))

                        when {
                            position == 0f -> {
                                translationX = DEFAULT_TRANSLATION_X
                                scaleX = DEFAULT_SCALE
                                scaleY = DEFAULT_SCALE
                                alpha = DEFAULT_ALPHA + position
                            }
                            position > 0f && position <= offscreenPageLimit - 1 -> {
                                val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
                                val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

                                scaleX = scaleFactor
                                scaleY = scaleFactor
                                translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                                alpha = alphaFactor
                            }
                            position < 0f && abs(position) <= offscreenPageLimit - 1 -> {
                                val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
                                val alphaFactor = ALPHA_FACTOR * (position) + DEFAULT_ALPHA

                                scaleX = scaleFactor
                                scaleY = 1 + (scaleFactor - 1) / 3
                                translationX = -(width / 1.6f) * position
                                alpha = alphaFactor * 0.9f
                            }
                        }
                    }
                }
            }

            TabLayoutMediator(tabs, viewPager) { tab, position ->
            }.attach()
        }
    }

    private fun onEventClick(it: String) {
        findNavController().navigate(EventsFragmentDirections.actionEventsFragmentToEventFragment())

    }

    companion object {

        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .3f
        private const val DEFAULT_ALPHA = 1f

    }
}