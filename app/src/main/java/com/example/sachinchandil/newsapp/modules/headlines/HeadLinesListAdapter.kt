package com.example.sachinchandil.newsapp.modules.headlines

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sachinchandil.newsapp.R
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import kotlinx.android.synthetic.main.item_news_list.view.*

class HeadLinesListAdapter(context: Context, private val newsItems: MutableList<ArticlesItem> = mutableListOf()) :
    RecyclerView.Adapter<HeadLinesListAdapter.NewsViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder =
        NewsViewHolder(layoutInflater.inflate(R.layout.item_news_list, p0, false))

    override fun onBindViewHolder(p0: NewsViewHolder, p1: Int) {
        p0.bind(newsItems[p1])
    }

    override fun getItemCount(): Int = newsItems.size

    class NewsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(articlesItem: ArticlesItem) {
            Glide.with(itemView).load(articlesItem.urlToImage)
                .apply(RequestOptions().fitCenter()).into(itemView.ivNewsThumbnail)
            itemView.tvNewsTitle.text = articlesItem.title
            itemView.tvTimestamp.text = articlesItem.publishedAt
            itemView.ivShare.setOnClickListener {
                it.context.startActivity(Intent.createChooser(getShareIntent(articlesItem.url), it.context.getString(R.string.send_to)))
            }
        }

        fun getShareIntent(text: String): Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
    }

    fun setItems(items: List<ArticlesItem>) {
        newsItems.clear()
        newsItems.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<ArticlesItem>) {
        newsItems.addAll(items)
        notifyItemRangeChanged(newsItems.size - items.size, newsItems.size)
    }
}
