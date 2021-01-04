package com.jhoander.hackernews.utils.base

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jhoander.hackernews.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiService {

    companion object {
        fun <T> build(
            client: OkHttpClient,
            serviceClass: Class<T>,
            urlBase: String
        ): T {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(OkHttpProfilerInterceptor())
            }
            val client1 = builder.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(urlBase)
                .client(client1)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(serviceClass)
        }

    }
}