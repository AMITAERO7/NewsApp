package com.hackernight.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackernight.newsapp.R
import com.hackernight.newsapp.ui.NewsActivity
import com.hackernight.newsapp.ui.viewmodel.NewsViewModel


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel:NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }

}