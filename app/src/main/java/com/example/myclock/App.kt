package com.example.myclock

import android.app.Application
import com.example.myclock.ex.GlobaleContext

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        GlobaleContext.init(this)
    }
}