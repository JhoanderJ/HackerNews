package com.jhoander.hackernews.di.component

import com.jhoander.hackernews.di.module.NewsDetailFragmentModule
import com.jhoander.hackernews.presentation.fragment.NewsDetailFragment
import com.jhoander.hackernews.utils.base.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsDetailFragmentModule::class])
interface NewsDetailFragmentComponent : FragmentComponent<NewsDetailFragment> {
}