package com.example.newsapp.presentation.view_news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.ActivityViewNewsBinding
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.extenctions.alert
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewNewsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityViewNewsBinding
    var article: Article? = null
    private val vm by viewModel<ViewNewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        article = intent.getSerializableExtra("object") as Article
        article?.let {
            setData(it)
        }
        vm.saveArticleResult.observe(this, Observer { observerSaveArticle(it) })
    }

    private fun observerSaveArticle(result: Result<Unit>) {
        if (result.isSuccess) {
            alert(
                Msg.SUCCESS,
                Msg.SAVE_TO_FAVOURITE
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
        } else {
            alert(
                Msg.ALERT,
                Msg.SOMETHING_WRONG
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
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
        binding.fabFavourite.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> finish()
            R.id.fab_favourite -> saveArticle()
        }
    }

    private fun saveArticle() {
        val user = vm.getUser(this)
        article?.userId = user?.id.toString()
        article?.let { vm.saveArticle(article = it) }
    }
}