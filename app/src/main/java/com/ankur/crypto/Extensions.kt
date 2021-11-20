package com.ankur.crypto

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Context.isNetworkConnected(): Boolean {
    //1
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    //2
    val activeNetwork = connectivityManager.activeNetwork
    //3
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    //4
    return networkCapabilities != null &&
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun String.openLinkInBrowser(context: Context?) {
    if (context == null) return
    val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    context.startActivity(urlIntent)
}

fun String.makeSnack(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT)
            .show()
}