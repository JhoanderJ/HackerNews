package com.jhoander.hackernews.domain.usecase

import com.jhoander.hackernews.data.repository.NewsListRepository
import com.jhoander.hackernews.domain.model.Article
import com.jhoander.hackernews.utils.base.UseCaseParam
import io.reactivex.Observable
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(private val newsListRepository: NewsListRepository) :
    UseCaseParam<Article, String>() {
    override fun createObservableUseCase(param: String): Observable<Article>? {
        return newsListRepository.getNews(param)
    }
}