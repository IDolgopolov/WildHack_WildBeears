package com.wildhuck.cumchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wildhuck.cumchatka.databinding.FragmentEventBinding

class EventFragment : Fragment() {
    private lateinit var binding: FragmentEventBinding
    private var eventsAdapter: EventsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            back.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}