package com.jhoander.hackernews.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhoander.hackernews.R
import com.jhoander.hackernews.di.component.DaggerNewsListFragmentComponent
import com.jhoander.hackernews.domain.model.Article
import com.jhoander.hackernews.presentation.adapter.NewsListAdapter
import com.jhoander.hackernews.presentation.viewmodel.NewsListViewModel
import com.jhoander.hackernews.utils.Failure
import com.jhoander.hackernews.utils.ResourceState
import com.jhoander.hackernews.utils.base.ViewModelFactory
import com.jhoander.hackernews.utils.base.getViewModel
import com.jhoander.hackernews.utils.extension.showMessage
import com.jhoander.hackernews.utils.extension.showProgress
import kotlinx.android.synthetic.main.news_list_fragment.*
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: NewsListViewModel

    lateinit var newsListAdapter: NewsListAdapter

    var newsDetailFragment: NewsDetailFragment = NewsDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependence()
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRv.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        viewModel.getNewsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.news_list_fragment, container, false)

    companion object {
        fun newInstance(): NewsListFragment {
            val instance = NewsListFragment()
            return instance
        }
    }

    private fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            it?.also {
                handleDogList(it.status, it.data, it.failure)
                pullToRefresh()
            }
        })
    }

    private fun pullToRefresh() {
        itemsSwipeToRefresh.setOnRefreshListener {
            viewModel.getNewsList()
            viewModel = getViewModel(viewModelFactory)
            viewModel.liveData.observe(this, Observer {
                it?.also {
                    handleDogList(it.status, it.data, it.failure)
                    itemsSwipeToRefresh.isRefreshing = false
                }
            })
        }
    }

    private fun handleDogList(status: ResourceState, data: Article?, failure: Failure?) {
        when (status) {
            ResourceState.LOADING -> {
                pbNews.showProgress(true, activity)
            }
            ResourceState.SUCCESS -> {
                pbNews.showProgress(false, activity)
                data?.let {
                    displayNews(data)
                }
            }
            ResourceState.ERROR -> {
                pbNews.showProgress(false, activity)
                when (failure) {
                    Failure.NetworkConnection -> {

                    }
                    Failure.ServerError -> {
                        pbNews.showProgress(false, activity)
                        showMessage((failure as Failure.Error).errorMessage, context)
                    }
                }
            }
            else -> {
            }
        }

    }

    private fun injectDependence() {
        DaggerNewsListFragmentComponent.builder().build().inject(this)
    }

    private fun displayNews(article: Article?) {
        newsListAdapter = NewsListAdapter {
            changeFragment(it)
        }
        newsRv.layoutManager = LinearLayoutManager(activity)
        newsRv.layoutManager = GridLayoutManager(context, 1)
        newsRv.adapter = newsListAdapter

        article?.let {
            newsListAdapter.setList(it.hits)
        }
    }

    private fun addFragment(fragment: Fragment?, idContainer: Int) {
        val trans: FragmentTransaction? = parentFragmentManager.beginTransaction()
        fragment?.let {
            trans?.add(idContainer, it)
        }
        trans?.addToBackStack(null)
        trans?.commit()
    }

    private fun changeFragment(url: String) {
        val b = Bundle()
        b.putString("story_url", url)
        newsDetailFragment.setArguments(b)
        addFragment(newsDetailFragment, R.id.fragmentContainer)
    }

}