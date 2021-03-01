package com.hackernight.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernight.newsapp.ui.Repository.NewsRepository
import com.hackernight.newsapp.ui.model.NewsResponse
import com.hackernight.newsapp.ui.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    private val _breakingNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    val breakingNews : LiveData<Resource<NewsResponse>> = _breakingNews

    private val _searchNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    val searchNews : LiveData<Resource<NewsResponse>> = _searchNews

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        //set the loading state
        _breakingNews.postValue(Resource.Loading())
        //get the response
        val response  = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        //parse the response and then set the value
        _breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch {
        _searchNews.postValue(Resource.Loading())
        //get the response
        val response  = newsRepository.searchNews(searchQuery,searchNewsPage)
        //parse the response
        _searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response:Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response:Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}