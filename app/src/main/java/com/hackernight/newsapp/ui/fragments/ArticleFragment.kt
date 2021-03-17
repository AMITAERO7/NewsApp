package com.hackernight.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.FragmentArticleBinding
import com.hackernight.newsapp.ui.NewsActivity
import com.hackernight.newsapp.ui.viewmodel.NewsViewModel


class ArticleFragment : Fragment() {

    lateinit var viewModel:NewsViewModel
    private var _binding : FragmentArticleBinding? = null
    val args :ArticleFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentArticleBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        _binding?.webView?.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}