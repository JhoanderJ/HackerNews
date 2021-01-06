package com.jhoander.hackernews.di.component

import com.jhoander.hackernews.di.module.NewsActivityModule
import com.jhoander.hackernews.presentation.activity.NewsActivity
import com.jhoander.hackernews.utils.base.ActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsActivityModule::class])
interface NewsActivityComponent : ActivityComponent<NewsActivity> {
}