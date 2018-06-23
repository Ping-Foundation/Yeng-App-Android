package `in`.yeng.user.helpers

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


object ConnectivityHelper {

    fun ifNotConnected(context: Context?, func: () -> Unit) {
        val manager: ConnectivityManager? = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager?.activeNetworkInfo
        if (info == null || !info.isConnectedOrConnecting) {
            func()
        }
    }

    fun ifConnected(context: Context?, func: () -> Unit) {
        val manager: ConnectivityManager? = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager?.activeNetworkInfo
        if (info != null && info.isConnectedOrConnecting) {
            func()
        }
    }
}