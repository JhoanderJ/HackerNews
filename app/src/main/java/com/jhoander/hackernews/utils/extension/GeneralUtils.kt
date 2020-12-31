package com.jhoander.hackernews.utils.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.jhoander.hackernews.utils.base.SafeClickListener

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl(url: String){
    Glide.with(context).load(url).into(this)
}

fun RelativeLayout.showProgress(show: Boolean, activity : FragmentActivity?) {
    if (show) {
        visibility = View.VISIBLE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    } else {
        visibility = View.GONE
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

fun showMessage(message: String, context: Context?) {
    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}