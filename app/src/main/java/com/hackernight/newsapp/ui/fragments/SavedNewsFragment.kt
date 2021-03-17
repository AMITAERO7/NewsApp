package com.hackernight.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.FragmentBreakingNewsBinding
import com.hackernight.newsapp.databinding.FragmentSavedNewsBinding
import com.hackernight.newsapp.ui.NewsActivity
import com.hackernight.newsapp.ui.adapter.NewsAdapter
import com.hackernight.newsapp.ui.viewmodel.NewsViewModel

class SavedNewsFragment : Fragment() {

    lateinit var viewModel:NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    private var _binding : FragmentSavedNewsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSavedNewsBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        setUpRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                    R.id.action_savedNewsFragment_to_articleFragment,
                    bundle
            )
        }

    }


    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        _binding?.rvSavedNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}