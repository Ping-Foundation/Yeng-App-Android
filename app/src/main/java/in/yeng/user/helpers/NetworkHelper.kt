package `in`.yeng.user.helpers

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSource
import okio.Okio
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object NetworkHelper {

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

    fun onPDFDownloaded(context: Context, url: String, func: () -> Unit) {

        doAsync {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val contents = response.body()?.bytes()
            val file = File(context.cacheDir.absolutePath + "/pdfFile.pdf")

            contents?.let {
                val oStream  = FileOutputStream(file).apply {
                    write(contents)
                    close()
                }

            }
            uiThread { func() }
        }
    }
}