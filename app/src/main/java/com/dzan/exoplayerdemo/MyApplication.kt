package com.dzan.exoplayerdemo

import android.app.Application
import com.dzan.exoplayerdemo.di.FirebaseModule
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication :Application() {
    @Inject
    lateinit var fireBaseModule : FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        fireBaseModule = FirebaseModule.provideFireBaseAnalytics()
    }
}