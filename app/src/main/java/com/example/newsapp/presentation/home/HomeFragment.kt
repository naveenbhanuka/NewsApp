package com.example.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.adapters.FilterAdapter
import com.example.newsapp.presentation.adapters.LatestNewsAdapter
import com.example.newsapp.presentation.adapters.NewsAdapter

class HomeFragment : Fragment(), FilterAdapter.OnItemClickListener {

    companion object {
        const val TAG = "homeFragment"
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var newsAdapter: NewsAdapter

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
    }

    private fun setupRecyclerViews() {
        val filters = mutableListOf<String>()
        filters.add("Health")
        filters.add("Technology")
        filters.add("Finance")
        filters.add("Art")
        filters.add("Country")

        filterAdapter = FilterAdapter(filters, this)

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

        newsAdapter = NewsAdapter()

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

    }

}