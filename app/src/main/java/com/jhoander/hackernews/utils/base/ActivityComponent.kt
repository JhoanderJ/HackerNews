package com.jhoander.hackernews.utils.base

import android.app.Activity

interface ActivityComponent<T : Activity> {
    /**
     * Indica que T requiere inyección desde este componente
     * @param target T
     */
    fun inject(target: T)
}