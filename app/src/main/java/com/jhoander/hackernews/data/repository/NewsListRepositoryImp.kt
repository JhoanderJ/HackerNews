package com.jhoander.hackernews.data.repository

import com.jhoander.hackernews.data.remote.NewsApi
import com.jhoander.hackernews.data.repository.mapper.ArticleEntityToDomainMapper
import com.jhoander.hackernews.domain.model.Article
import io.reactivex.Observable
import java.lang.Exception

class NewsListRepositoryImp(
    private val api: NewsApi,
    private val mapper: ArticleEntityToDomainMapper
) : NewsListRepository {
    override fun getNews(platform: String): Observable<Article> {
        return api.getNews(platform).map { response ->
            if (response.hits.isEmpty()) {
                throw Exception("Error")
            }
            mapper.map(response)
        }

    }
}