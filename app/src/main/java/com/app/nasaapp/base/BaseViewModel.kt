package com.app.nasaapp.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel


open class BaseViewModel(open val app: Application) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private var mApplication: Application? = null


    fun onProgressShow(activity: Activity) {

    }

    fun onProgressHide() {

    }

    /**
     * Return the application.
     */
    open fun <T : Application?> getApplication(): T {
        return app as T
    }
}