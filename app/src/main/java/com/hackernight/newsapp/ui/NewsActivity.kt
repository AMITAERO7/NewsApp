package com.hackernight.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.ActivityNewsBinding
import com.hackernight.newsapp.ui.Repository.NewsRepository
import com.hackernight.newsapp.ui.db.ArticleDatabase
import com.hackernight.newsapp.ui.viewmodel.NewsViewModel
import com.hackernight.newsapp.ui.viewmodel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel : NewsViewModel

    private  var _binding : ActivityNewsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        _binding?.bottomNavigationView?.setupWithNavController(findNavController(R.id.newsNavHostFragment))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}