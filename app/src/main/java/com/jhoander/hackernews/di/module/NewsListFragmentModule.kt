package com.jhoander.hackernews.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhoander.hackernews.presentation.presenter.NewsListViewModel
import com.jhoander.hackernews.utils.base.ViewModelFactory
import com.jhoander.hackernews.utils.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsListFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindCompaniesViewModel(viewModel: NewsListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}