package com.example.newsapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                        message
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
                        message
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
        vm.getLatestNews(pageSize = 5)
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
        vm.getAllNews(filters)
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }
}