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

    companion object {
        var event: Event? = null
    }

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
            promotionMediumTvTitle.text = event?.title
            promotionMediumTvBusiness.text = event?.text
            promotionMediumIvImage.setImageDrawable(event?.img)


            back.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}