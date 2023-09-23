package com.example.newsapp.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentFavouriteBinding
import com.example.newsapp.presentation.adapters.ViewLatestNewsAdapter
import com.example.newsapp.utill.extenctions.setActionBar

class FavouriteFragment : Fragment() {

    companion object {
        const val TAG = "favouriteFragment"
        fun newInstance() = FavouriteFragment()
    }

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var viewLatestNewsAdapter: ViewLatestNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewLatestNewsAdapter = ViewLatestNewsAdapter()

        binding.rvFavouriteNews.apply {
            adapter = viewLatestNewsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

}