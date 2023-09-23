package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.ItemViewNewsBinding

class ViewNewsAdapter : RecyclerView.Adapter<ViewNewsAdapter.ViewNewsAdapterViewHolder>() {

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
//        val article = differ.currentList[position]
//        holder.binding.apply {
//            tvTitle.text = article.title
//            tvDescription.text = article.description
//            tvAuthor.text = article.source.name
//            Glide.with(holder.binding.root).load(article.urlToImage).into(ivArticleImage)
//        }
    }

    override fun getItemCount() = 12 /*differ.currentList.size*/
}
