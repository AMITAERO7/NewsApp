package com.hackernight.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.ItemArticlePreviewBinding
import com.hackernight.newsapp.ui.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    //compares the two list and update only difference of two lists in background thread
    private val differCallBack = object :DiffUtil.ItemCallback<Article>(){

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    //to find the list difference
    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.url).into(holder.ivArticleImage)
            holder.tvSource.text = article.source.name
            holder.tvTitle.text = article.title
            holder.tvDescription.text = article.description
            holder.tvPublishedAt.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener : (Article) -> Unit) {
        onItemClickListener = listener
    }




    inner class ArticleViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var ivArticleImage = itemView.findViewById<ImageView>(R.id.ivArticleImage)
        var tvSource = itemView.findViewById<TextView>(R.id.tvSource)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var tvPublishedAt = itemView.findViewById<TextView>(R.id.tvPublishedAt)
    }


}