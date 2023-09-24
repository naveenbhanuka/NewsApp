package com.example.newsapp.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.databinding.FragmentFavouriteBinding
import com.example.newsapp.presentation.adapters.ViewLatestNewsAdapter
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.extenctions.alert
import com.example.newsapp.utill.extenctions.gone
import com.example.newsapp.utill.extenctions.setActionBar
import com.example.newsapp.utill.extenctions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    companion object {
        const val TAG = "favouriteFragment"
        fun newInstance() = FavouriteFragment()
    }

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var viewLatestNewsAdapter: ViewLatestNewsAdapter
    private val vm by viewModel<FavouriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)
        vm.getSaveNewsResult.observe(viewLifecycleOwner, Observer { observerGetAllArticles(it) })
        return binding.root
    }

    private fun observerGetAllArticles(result:List<Article>) {
        viewLatestNewsAdapter.submitList(result)

        if (result.isEmpty()){
            binding.tvNoDataFavouriteNews.visible()
            binding.rvFavouriteNews.gone()
        }else {
            binding.tvNoDataFavouriteNews.gone()
            binding.rvFavouriteNews.visible()
        }
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

    override fun onResume() {
        super.onResume()
        vm.getSavedNews()
    }

}