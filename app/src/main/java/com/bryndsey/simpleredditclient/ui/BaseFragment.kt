package com.bryndsey.simpleredditclient.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseFragment: Fragment() {

    private val compositeDisposable = CompositeDisposable()

    protected fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.clear()
    }
}