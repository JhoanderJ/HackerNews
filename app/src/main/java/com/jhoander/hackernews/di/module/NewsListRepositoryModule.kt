package com.jhoander.hackernews.di.module

import com.jhoander.hackernews.data.remote.NewsApi
import com.jhoander.hackernews.data.repository.NewsListRepository
import com.jhoander.hackernews.data.repository.NewsListRepositoryImp
import com.jhoander.hackernews.utils.base.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NewsListRepositoryModule {

    @Provides
    fun provideRepository(
        api: NewsApi
    ): NewsListRepository {
        return NewsListRepositoryImp(api)
    }

    @Provides
    fun provideApiService(): NewsApi {
        OkHttpClient()
        return ApiService.build(
            NewsApi::class.java,
            "https://hn.algolia.com/api/v1/"
        )
    }
}