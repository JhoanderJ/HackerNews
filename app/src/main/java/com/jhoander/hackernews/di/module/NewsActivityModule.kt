package com.jhoander.hackernews.di.module

import com.jhoander.hackernews.presentation.fragment.NewsListFragment
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule {

    @Provides
    fun provideFragment(): NewsListFragment {
        return NewsListFragment.newInstance()
    }
}