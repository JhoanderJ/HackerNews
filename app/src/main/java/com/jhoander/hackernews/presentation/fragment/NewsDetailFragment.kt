package com.jhoander.hackernews.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.jhoander.hackernews.R
import com.jhoander.hackernews.di.component.DaggerNewsDetailFragmentComponent
import com.jhoander.hackernews.utils.extension.showProgress
import kotlinx.android.synthetic.main.news_detail_fragment.*


class NewsDetailFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependence()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.news_detail_fragment, container, false)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pbNews.showProgress(true, activity)
        val url = arguments?.getString("story_url")
        webView = webview
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                pbNews.showProgress(false, activity)
            }
        }
        webView.loadUrl(url)
        iv_back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

    }

    private fun injectDependence() {
        DaggerNewsDetailFragmentComponent.builder().build().inject(this)
    }

}






