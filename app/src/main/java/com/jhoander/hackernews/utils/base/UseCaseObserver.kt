package com.jhoander.hackernews.utils.base

import io.reactivex.observers.DisposableObserver

abstract class UseCaseObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {}

    override fun onNext(t: T) {}

    override fun onError(e: Throwable) {}
}