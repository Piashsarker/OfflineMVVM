package com.piash.sarker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.hilt.android.qualifiers.ApplicationContext

class Utils {
    companion object{

        fun isNetworkAvailable(app: Context): Boolean {
            val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            return info != null && info.isAvailable
        }
    }
}