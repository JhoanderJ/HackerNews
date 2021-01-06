package com.jhoander.hackernews.presentation.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhoander.hackernews.domain.model.Article
import com.jhoander.hackernews.domain.usecase.GetNewsListUseCase
import com.jhoander.hackernews.utils.Failure
import com.jhoander.hackernews.utils.Resource
import com.jhoander.hackernews.utils.ResourceState
import com.jhoander.hackernews.utils.base.UseCaseObserver
import com.jhoander.hackernews.utils.post
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val getNewsListUseCase: GetNewsListUseCase) :
    ViewModel() {

    var liveData: MutableLiveData<Resource<Article>> = MutableLiveData()

    fun getNewsList() {
        liveData.post(status = ResourceState.LOADING)
        getNewsListUseCase.execute("android", object : UseCaseObserver<Article>() {
            override fun onNext(value: Article) {
                liveData.post(status = ResourceState.SUCCESS, data = value)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                liveData.post(
                    status = ResourceState.ERROR, failure = Failure.Error(
                        "ha ocurrido un error inesperado intente nuevamente"
                    )
                )
            }
        })

    }

    override fun onCleared() {
        getNewsListUseCase.dispose()
    }
}
