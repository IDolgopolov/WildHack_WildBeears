package com.wildhuck.cumchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment, container)

        val viewPager = inflate.findViewById<ViewPager2>(R.id.viewPager)
        val tabs = inflate.findViewById<TabLayout>(R.id.tabs)

        viewPager.apply {
            adapter = CustomAdapter()
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


        return inflate
    }

    companion object {

        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .3f
        private const val DEFAULT_ALPHA = 1f

    }


    private class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(
                LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.promotionMediumTvDuration).text =
                position.toString()
        }

        override fun getItemCount() = 20
    }
}