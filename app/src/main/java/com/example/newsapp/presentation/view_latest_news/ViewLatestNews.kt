package com.example.newsapp.presentation.view_latest_news

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.databinding.ActivityViewLatestNewsBinding
import com.example.newsapp.presentation.adapters.ViewLatestNewsAdapter
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.utill.Constant
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.Resource
import com.example.newsapp.utill.extenctions.alert
import com.example.newsapp.utill.extenctions.gone
import com.example.newsapp.utill.extenctions.setActionBar
import com.example.newsapp.utill.extenctions.visible
import com.example.newsapp.utill.extenctions.withNetwork
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
        getAllLatestNews()

    }

    private fun getAllLatestNews() {
        withNetwork({
            vm.getLatestNews(null)
        }, {
            alert(
                Msg.ALERT,
                Msg.INTERNET_ISSUE
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
        })
    }

    private fun observerGetLatestNews(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data?.let { newsResponse ->
                    viewLatestNewsAdapter.submitList(newsResponse.articles.toList())
                    val totalPages = newsResponse.totalResults / Constant.QUERY_PAGE_SIZE + 2
                    isLastPage = vm.latestNewsPage == totalPages
                    if (newsResponse.totalResults < 0){
                        binding.tvNoDataLatestNews.visible()
                        binding.rvViewNews.gone()
                    }else {
                        binding.tvNoDataLatestNews.gone()
                        binding.rvViewNews.visible()
                    }
                }
            }

            is Resource.Error -> {
                hideProgressBar()
                resource.message?.let { message ->
                    alert(
                        Msg.ALERT,
                        Msg.SOMETHING_WRONG
                    ) {
                        positiveButton(Msg.BUTTON_OK) {
                        }
                    }.show()
                }
            }

            is Resource.Loading -> {
                showProgressBar()
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
            addOnScrollListener(this@ViewLatestNews.scrollListener)
        }
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
        isLastPage = false
    }

    private fun showProgressBar() {
        binding.progressCircular.visible()
        isLastPage = true
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoErrors = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constant.QUERY_PAGE_SIZE
            val shouldPaginate = isNoErrors && isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                getAllLatestNews()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

}