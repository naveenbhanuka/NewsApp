package com.example.newsapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.favourite.FavouriteFragment
import com.example.newsapp.presentation.home.HomeFragment
import com.example.newsapp.presentation.profile.ProfileFragment
import com.example.newsapp.utill.extenctions.currentFragment
import com.example.newsapp.utill.extenctions.replaceFragment
import com.example.newsapp.utill.extenctions.setActionBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        openHome()
        initBottomNavigation()

    }

    private fun openHome() {
        supportFragmentManager.replaceFragment(
            R.id.fl_main,
            HomeFragment.newInstance(),
            HomeFragment.TAG
        )
    }

    private fun initBottomNavigation() {
        binding.bnView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home ->{
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        HomeFragment.newInstance(),
                        HomeFragment.TAG
                    )
                }

                R.id.nav_favourite ->{
                    supportActionBar?.setActionBar(
                        this,
                        getString(R.string.label_nav_favourite),
                        false,
                        isCenterTitle = true
                    )
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        FavouriteFragment.newInstance(),
                        FavouriteFragment.TAG
                    )
                }

                R.id.nav_profile ->{
                    supportFragmentManager.replaceFragment(
                        R.id.fl_main,
                        ProfileFragment.newInstance(),
                        ProfileFragment.TAG
                    )
                }

            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.currentFragment(R.id.fl_main) is HomeFragment) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }
}