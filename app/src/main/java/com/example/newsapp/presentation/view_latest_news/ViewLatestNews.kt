package com.example.newsapp.presentation.view_latest_news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.databinding.ActivityViewLatestNewsBinding
import com.example.newsapp.presentation.adapters.ViewLatestNewsAdapter
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.Resource
import com.example.newsapp.utill.extenctions.alert
import com.example.newsapp.utill.extenctions.setActionBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewLatestNews : AppCompatActivity() {

    private lateinit var binding: ActivityViewLatestNewsBinding
    private lateinit var viewLatestNewsAdapter: ViewLatestNewsAdapter
    private val vm by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewLatestNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        vm.latestNews.observe(this, Observer { observerGetLatestNews(it) })
        vm.getLatestNews(null)
    }

    private fun observerGetLatestNews(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                //   hideProgressBar()
                resource.data?.let { newsResponse ->
                    viewLatestNewsAdapter.submitList(newsResponse.articles)
                }
            }

            is Resource.Error -> {
//                    hideProgressBar()
                resource.message?.let { message ->
                    alert(
                        Msg.ALERT,
                        message
                    ) {
                        positiveButton(Msg.BUTTON_OK) {
                        }
                    }.show()
                }
            }

            is Resource.Loading -> {
                // showProgressBar()
            }
        }
    }

    private fun initUI() {

        setSupportActionBar(binding.toolbarLatestNews)
        supportActionBar?.setActionBar(
            this,
            getString(R.string.label_hot_updates),
            true
        )

        viewLatestNewsAdapter = ViewLatestNewsAdapter()

        binding.rvViewNews.apply {
            adapter = viewLatestNewsAdapter
            layoutManager = LinearLayoutManager(
                this@ViewLatestNews,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

}