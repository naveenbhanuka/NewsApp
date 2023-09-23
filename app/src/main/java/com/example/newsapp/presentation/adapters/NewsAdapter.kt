package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.domain.model.DArticle
import com.example.newsapp.utill.extenctions.DD_MMMM_EEEE
import com.example.newsapp.utill.extenctions.DD_MMMM_yy
import com.example.newsapp.utill.extenctions.toCustomDate

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(
        val binding: ItemNewsBinding
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            tvNewsTitle.text = article.title
            tvAuthor.text = article.author
            tvPublishDate.text = article.publishedAt.toCustomDate(DD_MMMM_yy)
            Glide.with(holder.binding.root).load(article.urlToImage).into(ivNewsArticleImage)
        }
    }

    override fun getItemCount() =   differ.currentList.size
}
