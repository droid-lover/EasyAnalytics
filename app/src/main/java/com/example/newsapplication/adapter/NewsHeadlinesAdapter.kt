package com.example.newsapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.models.Articles
import com.example.newsapplication.utils.C
import com.example.newsapplication.views.activities.NewsDetailActivity
import kotlinx.android.synthetic.main.item_mix_news.view.*
import kotlinx.android.synthetic.main.item_news_headline.view.*
import kotlinx.android.synthetic.main.item_news_headline.view.cvContainer
import java.text.SimpleDateFormat


/**
 * Created by Sachin.
 */
class NewsHeadlinesAdapter(private val context: Context, private val newsHeadlines: List<Articles>, private val viewType: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TOP_HEADLINES -> TypeTopHeadlinesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_headline, parent, false))
            else -> MixNewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mix_news, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        newsHeadlines.also {
            when (holder) {
                is TypeTopHeadlinesViewHolder -> holder.bindToView(it[position])
                is MixNewsViewHolder -> holder.bindToView(it[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return newsHeadlines.size ?: 0
    }

    inner class TypeTopHeadlinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivNewsCover = itemView.ivNewsCover!!
        private val tvNewsDescription = itemView.tvNewsDescription!!
        private val tvPublishedDate = itemView.tvPublishedDate!!
        private val cvContainer = itemView.cvContainer!!
        fun bindToView(article: Articles) {
            article.urlToImage?.also { ivNewsCover.setImageURI(it) }
            article.description?.also { tvNewsDescription.text = it }
            article.publishedAt?.also { tvPublishedDate.text = getDayFromDate(it) }

            cvContainer.setOnClickListener {
                context.startActivity(Intent(context,NewsDetailActivity::class.java).putExtra(
                    C.NEWS_DATA,article
                ))
            }
        }
    }

    inner class MixNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cvMixNewsContainer = itemView.cvMixNewsContainer!!
        private val sdvMixNewsImg = itemView.sdvMixNewsImg!!
        private val tvMixNewsDate = itemView.tvMixNewsDate!!
        fun bindToView(article: Articles) {
            article.urlToImage?.also { sdvMixNewsImg.setImageURI(it) }
//            article.description?.also { tvNewsDescription.text = it }
            article.publishedAt?.also { tvMixNewsDate.text = getDayFromDate(it) }

            cvMixNewsContainer.setOnClickListener {
                context.startActivity(Intent(context, NewsDetailActivity::class.java).putExtra(
                    C.NEWS_DATA,article
                ))
            }
        }
    }

    private fun getDayFromDate(dateValue: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateValue)
        return SimpleDateFormat("dd-MM-yyyy").format(date)
    }

    companion object {
        const val TYPE_TOP_HEADLINES = 0
        const val TYPE_MIX_NEWS = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewType) {
            TYPE_TOP_HEADLINES
        } else {
            TYPE_MIX_NEWS
        }
    }

}