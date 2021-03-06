package com.hackernight.newsapp.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    //TODO Room doesn't understand "Source" type so added type converter such tha Source -> String and String -> Source
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
) : Serializable