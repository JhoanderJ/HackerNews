package com.jhoander.hackernews.di.component

import com.jhoander.hackernews.di.module.NewsListFragmentModule
import com.jhoander.hackernews.di.module.NewsListRepositoryModule
import com.jhoander.hackernews.presentation.fragment.NewsListFragment
import com.jhoander.hackernews.utils.base.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsListFragmentModule::class, NewsListRepositoryModule::class])
interface NewsListFragmentComponent : FragmentComponent<NewsListFragment> {
}