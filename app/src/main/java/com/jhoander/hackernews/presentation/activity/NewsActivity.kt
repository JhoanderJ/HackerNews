package com.jhoander.hackernews.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.jhoander.hackernews.R
import com.jhoander.hackernews.di.component.DaggerNewsActivityComponent
import com.jhoander.hackernews.presentation.fragment.NewsListFragment
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListFragment: NewsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.news_activity)
        setFragment(R.id.fragmentContainer, newsListFragment, "fragmentList")

    }

    private fun setFragment(idContainer: Int, fragment: Fragment, tag: String) {
        val trans: FragmentTransaction = supportFragmentManager.beginTransaction()
        trans.replace(idContainer, fragment, tag)
        trans.commit()
    }

    private fun injectDependencies() {
        DaggerNewsActivityComponent.builder().build().inject(this)

    }
}
