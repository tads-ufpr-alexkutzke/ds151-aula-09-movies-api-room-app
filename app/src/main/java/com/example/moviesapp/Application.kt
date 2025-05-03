package com.example.moviesapp

import android.app.Application

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContextHolder.init(this)
    }
}