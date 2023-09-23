package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.domain.model.DArticle

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(
        val binding: ItemNewsBinding
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val article = differ.currentList[position]
//        holder.binding.apply {
//            tvNewsTitle.text = article.title
//            tvAuthor.text = article.author
//            tvPublishDate.text = article.publishedAt
//        }
    }

    override fun getItemCount() =  10 /*differ.currentList.size*/
}
