package com.example.newsapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.ItemViewNewsBinding

class ViewLatestNewsAdapter : RecyclerView.Adapter<ViewLatestNewsAdapter.ViewNewsAdapterViewHolder>() {

    inner class ViewNewsAdapterViewHolder(
        val binding: ItemViewNewsBinding
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewNewsAdapterViewHolder {
        return ViewNewsAdapterViewHolder(
            ItemViewNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewNewsAdapterViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvAuthor.text = article.source.name
            Glide.with(holder.binding.root)
                .load(article.urlToImage)
                .apply(
                    RequestOptions().transform(RoundedCorners(10))
                )
                .into(holder.binding.ivArticleImage)
        }
    }

    override fun getItemCount() =  differ.currentList.size
}
