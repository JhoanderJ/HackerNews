package com.jhoander.hackernews.utils

import androidx.lifecycle.MutableLiveData
import com.jhoander.hackernews.utils.Failure.FeatureFailure

enum class ResourceState {
    LOADING,
    LOADING_REFRESH,
    SUCCESS,
    PAGING_SUCCESS,
    ERROR,
    PAGING_ERROR,
    REFRESH_TOKEN
}

open class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val failure: Failure?
)

@Suppress("UNCHECKED_CAST")
fun <T> MutableLiveData<T>.post(
    status: ResourceState,
    data: Any? = null,
    failure: Failure? = null
) {
    this.postValue(Resource(status, data, failure) as T)
}

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    data class Error(val errorMessage: String) : Failure()
    object NetworkConnection : Failure()
    object ServerError : Failure()

    /* * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}