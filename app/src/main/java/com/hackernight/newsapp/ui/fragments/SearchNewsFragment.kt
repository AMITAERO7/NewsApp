package com.hackernight.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.FragmentSearchNewsBinding
import com.hackernight.newsapp.ui.NewsActivity
import com.hackernight.newsapp.ui.adapter.NewsAdapter
import com.hackernight.newsapp.ui.util.Resource
import com.hackernight.newsapp.ui.viewmodel.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment() {

    lateinit var viewModel:NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    private var _binding : FragmentSearchNewsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchNewsBinding.inflate(inflater,container,false)
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
                    R.id.action_searchNewsFragment_to_articleFragment,
                    bundle
            )
        }

        //implement search fun with delay of 5 sec
        var job :Job? = null

        _binding?.etSearch?.addTextChangedListener { editable ->
            job = MainScope().launch {
                delay(5000)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgress()
                    response.message?.let { message ->
                        // Toast.makeText(requireContext(), "Error $message", Toast.LENGTH_SHORT).show()
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
        _binding?.rvSearchNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}