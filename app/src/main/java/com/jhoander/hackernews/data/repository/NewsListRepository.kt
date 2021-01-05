package com.jhoander.hackernews.data.repository

import com.jhoander.hackernews.domain.model.Article
import io.reactivex.Observable

interface NewsListRepository {

    fun getNews(platform: String): Observable<Article>
}