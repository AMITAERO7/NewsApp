package com.hackernight.newsapp.ui.Repository

import com.hackernight.newsapp.ui.api.NewsClient
import com.hackernight.newsapp.ui.db.ArticleDatabase
import com.hackernight.newsapp.ui.model.Article
import retrofit2.Retrofit

class NewsRepository(val db:ArticleDatabase) {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int) =
            NewsClient.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber: Int) =
            NewsClient.api.searchNews(searchQuery, pageNumber)

    suspend fun upsert(article:Article) =
            db.getArticleDao().upsert(article)

    fun getSavedNews() =
            db.getArticleDao().getAllArticles()

    suspend fun deleteNews(article: Article) =
            db.getArticleDao().deleteArticles(article)
}