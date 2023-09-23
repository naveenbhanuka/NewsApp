package com.example.newsapp.presentation.view_latest_news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityViewLatestNewsBinding
import com.example.newsapp.presentation.adapters.ViewNewsAdapter
import com.example.newsapp.utill.extenctions.setActionBar

class ViewLatestNews : AppCompatActivity() {

    private lateinit var binding: ActivityViewLatestNewsBinding
    private lateinit var viewNewsAdapter: ViewNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewLatestNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {

        setSupportActionBar(binding.toolbarLatestNews)
        supportActionBar?.setActionBar(
            this,
            getString(R.string.label_hot_updates),
            true
        )

        viewNewsAdapter = ViewNewsAdapter()

        binding.rvViewNews.apply {
            adapter = viewNewsAdapter
            layoutManager = LinearLayoutManager(
                this@ViewLatestNews,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

}