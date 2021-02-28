package com.hackernight.newsapp.ui.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hackernight.newsapp.ui.model.Article

@Dao
interface ArticleDao {
    //insert a new record or update the record and returns the ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    //suspend not works with LiveData and LiveData to bear the screen rotation
    @Query("select * from article")
    fun getAllArticles():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticles(article: Article)
}