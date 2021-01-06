package com.jhoander.hackernews.data.remote

import com.jhoander.hackernews.domain.model.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("search_by_date")
    fun getNews(@Query("query") platform: String): Observable<Article>
}