package com.hackernight.newsapp.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.FragmentBreakingNewsBinding
import com.hackernight.newsapp.ui.NewsActivity
import com.hackernight.newsapp.ui.adapter.NewsAdapter
import com.hackernight.newsapp.ui.util.Resource
import com.hackernight.newsapp.ui.viewmodel.NewsViewModel

class BreakingNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    private var _binding :FragmentBreakingNewsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get Fragment's Activity's viewmodel
        viewModel = (activity as NewsActivity).viewModel

        setUpRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                    R.id.action_breakingNewsFragment_to_articleFragment,
                    bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgress()
                    response.message?.let { message ->
                       Toast.makeText(requireContext(), "Error $message", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgress()
                }
            }
        })

    }

    private fun hideProgress() {
        _binding?.paginationProgressBar?.visibility = View.INVISIBLE
    }

    private fun showProgress() {
        _binding?.paginationProgressBar?.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        _binding?.rvBreakingNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}