package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemLatestNewsBinding
import com.example.newsapp.domain.model.DArticle

class LatestNewsAdapter : RecyclerView.Adapter<LatestNewsAdapter.LatestNewsViewHolder>() {

    inner class LatestNewsViewHolder(
        val binding: ItemLatestNewsBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<DArticle>() {
        override fun areItemsTheSame(oldItem: DArticle, newItem: DArticle): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: DArticle, newItem: DArticle): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<DArticle>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsViewHolder {
        return LatestNewsViewHolder(
            ItemLatestNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) {
//        val article = differ.currentList[position]
//        holder.binding.apply {
//            tvTitle.text = article.title
//            tvDescription.text = article.description
//            tvSource.text = article.source.name
//        }
    }

    override fun getItemCount() = 5 /*differ.currentList.size*/
}
