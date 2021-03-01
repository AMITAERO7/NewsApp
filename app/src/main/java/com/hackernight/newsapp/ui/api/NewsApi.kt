package com.hackernight.newsapp.ui.api

import com.hackernight.newsapp.ui.model.NewsResponse
import com.hackernight.newsapp.ui.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    // http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=72e5121161c34a129d588e064e4d12d9

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String = "us",
        @Query("page")
        pageNumber:Int = 1,
        @Query("apiKey")
        apiKey:String = Constants.API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber:Int = 1,
        @Query("apiKey")
        apiKey:String = Constants.API_KEY
    ) : Response<NewsResponse>

}