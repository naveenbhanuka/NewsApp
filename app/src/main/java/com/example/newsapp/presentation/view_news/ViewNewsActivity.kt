package com.example.newsapp.presentation.view_news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityViewNewsBinding
import com.example.newsapp.utill.extenctions.setActionBar

class ViewNewsActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityViewNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()


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