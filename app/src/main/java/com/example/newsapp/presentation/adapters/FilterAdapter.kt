package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemFiltersBinding

class FilterAdapter(
    private val filterList: MutableList<String>,
    val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemFiltersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvCategory.text = item
            binding.root.setOnClickListener {
                onClickListener.onFilterClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFiltersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterList[position])
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    interface OnItemClickListener {
        fun onFilterClick(item: String)
    }
}