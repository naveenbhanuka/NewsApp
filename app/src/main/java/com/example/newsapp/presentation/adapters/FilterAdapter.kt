package com.example.newsapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemFiltersBinding

class FilterAdapter(
    val context: Context,
    private val filterList: MutableList<String>,
    val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private var selectedIndex = 0

    inner class ViewHolder(private val binding: ItemFiltersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, position: Int) {
            binding.tvCategory.text = item
            binding.root.setOnClickListener {
                if (selectedIndex != position) {
                    val previousSelectedIndex = selectedIndex
                    selectedIndex = position
                    notifyItemChanged(previousSelectedIndex)
                    notifyItemChanged(position)
                    onClickListener.onFilterClick(item)
                }
            }

            if (selectedIndex == position) {
                binding.containerMain.setBackgroundResource(R.drawable.bg_blue_selected_filter)
                binding.tvCategory.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
            } else {
                binding.containerMain.setBackgroundResource(R.drawable.bg_filter)
                binding.tvCategory.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorDarkGray
                    )
                )
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFiltersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterList[position], position)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    interface OnItemClickListener {
        fun onFilterClick(item: String)
    }
}