package com.jhoander.hackernews.data.repository

import com.jhoander.hackernews.data.remote.NewsApi
import com.jhoander.hackernews.domain.model.Article
import io.reactivex.Observable

class NewsListRepositoryImp(
    private val api: NewsApi
) : NewsListRepository {
    override fun getNews(platform: String): Observable<Article> {
        return api.getNews(platform).map {
            if (it.hits.isEmpty()) {
                throw Exception("Error")
            }
            it
        }

    }

}
