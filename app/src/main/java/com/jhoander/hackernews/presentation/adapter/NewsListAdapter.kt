package com.jhoander.hackernews.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhoander.hackernews.R
import com.jhoander.hackernews.domain.model.Hit
import com.jhoander.hackernews.utils.extension.inflate
import com.jhoander.hackernews.utils.extension.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_news_list.view.*


class NewsListAdapter constructor(val listener: (String) -> Unit) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private lateinit var hits: List<Hit>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.item_news_list)
        )
    }

    override fun getItemCount(): Int {
        return hits.size
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.initView(hits[position], position)
    }

    fun setList(hits: List<Hit>) {
        this.hits = hits
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initView(item: Hit?, pos: Int) {
            if (item == null) {
                itemView.visibility = View.GONE
                return
            }
            itemView.newsTitle.text = item.title
            itemView.newsTitle.text = item.story_title
            itemView.newsAuthor.text = item.author
            itemView.newsCreatedAt.text = item.created_at

            itemView.setSafeOnClickListener {
                //TODO For cases in which the story_url field is null, it will not be possible to view detail in WebView
                hits[adapterPosition].story_url?.let { view ->
                    listener(view)
                }
            }

        }

    }
}