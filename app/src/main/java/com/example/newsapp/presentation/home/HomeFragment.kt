package com.example.newsapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.data.datasource.dto.NewsResponse
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.adapters.FilterAdapter
import com.example.newsapp.presentation.adapters.LatestNewsAdapter
import com.example.newsapp.presentation.adapters.NewsAdapter
import com.example.newsapp.presentation.view_latest_news.ViewLatestNews
import com.example.newsapp.presentation.view_news.ViewNewsActivity
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.Resource
import com.example.newsapp.utill.extenctions.alert
import com.example.newsapp.utill.extenctions.gone
import com.example.newsapp.utill.extenctions.hasInternetConnection
import com.example.newsapp.utill.extenctions.visible
import com.example.newsapp.utill.extenctions.withNetwork
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), FilterAdapter.OnItemClickListener, NewsAdapter.OnItemClickListener,
    View.OnClickListener {

    companion object {
        const val TAG = "homeFragment"
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var newsAdapter: NewsAdapter
    private val vm by viewModel<HomeViewModel>()
    var searchListSize = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        initUI()

        vm.latestNews.observe(viewLifecycleOwner, Observer { observerGetLatestNews(it) })
        vm.getAllNews.observe(viewLifecycleOwner, Observer { observerGetAllNews(it) })

        handleSearchView()
    }

    private fun handleSearchView() {
        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getNews(query).also {
                    updateUI(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    handleSearchTextCleared()
                }
                return false
            }

        }
        )
    }

    private fun updateUI(query: String?) {
        showProgressBar()
        binding.tvLabelLatestNews.gone()
        binding.tvLabelSeeAllLatestNews.gone()
        binding.ivSeeAll.gone()
        binding.rvLatestNews.gone()
        binding.rvFilters.gone()
        binding.tvLabelSearchResults.visible()
        binding.tvLabelSearchResults.text = "Search results for $query"
        hideProgressBar()
    }

    private fun handleSearchTextCleared() {
        showProgressBar()
        binding.tvLabelLatestNews.visible()
        binding.tvLabelSeeAllLatestNews.visible()
        binding.ivSeeAll.visible()
        binding.rvLatestNews.visible()
        binding.rvFilters.visible()
        binding.tvLabelSearchResults.gone()
        getNews("Health")
        hideProgressBar()
    }

    private fun initUI() {
        binding.tvLabelSeeAllLatestNews.setOnClickListener(this)
        binding.ivSeeAll.setOnClickListener(this)
    }

    private fun observerGetAllNews(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data?.let { newsResponse ->
                    newsAdapter.submitList(newsResponse.articles)
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

    private fun observerGetLatestNews(resource: Resource<NewsResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data?.let { newsResponse ->
                    latestNewsAdapter.submitList(newsResponse.articles)
                }.also {
                    getNews(getString(R.string.label_health))
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

    private fun setupRecyclerViews() {
        val filters = mutableListOf<String>()
        filters.add("Health")
        filters.add("Technology")
        filters.add("Finance")
        filters.add("Art")
        filters.add("Country")

        filterAdapter = FilterAdapter(requireContext(), filters, this)

        binding.rvFilters.apply {
            adapter = filterAdapter
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        latestNewsAdapter = LatestNewsAdapter()

        binding.rvLatestNews.apply {
            adapter = latestNewsAdapter
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        newsAdapter = NewsAdapter(this)

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun onFilterClick(item: String) {
        getNews(item)
    }

    override fun onResume() {
        super.onResume()
        getLatestNews()
    }

    private fun getLatestNews(){
        requireContext().withNetwork({
            vm.getLatestNews(pageSize = 5)
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

    override fun onNewsClick(article: Article) {
        Intent(requireContext(), ViewNewsActivity::class.java).apply {
            putExtra("object", article)
            requireActivity().startActivity(this)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_label_see_all_latest_news, R.id.iv_see_all -> gotoViewAllNews()
        }
    }

    private fun gotoViewAllNews() {
        startActivity(Intent(requireContext(), ViewLatestNews::class.java))
    }

    private fun getNews(filters: String?) {
        requireContext().withNetwork({
            vm.getAllNews(filters)
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

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }
}