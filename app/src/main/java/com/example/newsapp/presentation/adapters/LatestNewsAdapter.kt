package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.ItemLatestNewsBinding
import com.example.newsapp.domain.model.DArticle

class LatestNewsAdapter : RecyclerView.Adapter<LatestNewsAdapter.LatestNewsViewHolder>() {

    inner class LatestNewsViewHolder(
        val binding: ItemLatestNewsBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

     private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Article>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsViewHolder {
        return LatestNewsViewHolder(
            ItemLatestNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvSource.text = article.source.name
            Glide.with(holder.binding.root).load(article.urlToImage).into(ivArticleImage)
        }
    }

    override fun getItemCount() =  differ.currentList.size
}
