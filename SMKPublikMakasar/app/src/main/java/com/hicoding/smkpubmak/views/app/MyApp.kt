package com.hicoding.smkpubmak.views.app

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.androidnetworking.AndroidNetworking
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient

class MyApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(ChuckInterceptor(this))
            .build()

        AndroidNetworking.initialize(applicationContext, okHttpClient)
    }
}