package com.example.newsapp.presentation.view_news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.ActivityViewNewsBinding
import com.example.newsapp.utill.extenctions.setActionBar

class ViewNewsActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityViewNewsBinding
    var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        article = intent.getSerializableExtra("object") as Article
        article?.let {
            setData(it)
        }




    }

    private fun setData(article: Article) {
        Glide.with(this).load(article.urlToImage).into(binding.ivNewsImage)
        binding.tvTitle.text = article.title
        binding.tvSource.text = article.source.name
        binding.tvAuthor.text = article.author
        binding.tvNewsDescription.text = article.description
    }

    private fun initUI() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorTransparent)
        binding.ivBack.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.iv_back -> finish()
        }
    }
}