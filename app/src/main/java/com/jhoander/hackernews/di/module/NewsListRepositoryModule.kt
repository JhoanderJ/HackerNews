package com.jhoander.hackernews.di.module

import com.jhoander.hackernews.data.remote.NewsApi
import com.jhoander.hackernews.data.repository.NewsListRepository
import com.jhoander.hackernews.data.repository.NewsListRepositoryImp
import com.jhoander.hackernews.data.repository.mapper.ArticleEntityToDomainMapper
import com.jhoander.hackernews.utils.base.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NewsListRepositoryModule {

    @Provides
    fun provideRepository(
        api: NewsApi, mapper: ArticleEntityToDomainMapper
    ): NewsListRepository {
        return NewsListRepositoryImp(api,mapper)
    }

    @Provides
    fun provideApiService(): NewsApi {
        val okHttpClient = OkHttpClient()
        return ApiService.build(
            okHttpClient,
            NewsApi::class.java,
            "https://hn.algolia.com/api/v1/"
        )
    }
}