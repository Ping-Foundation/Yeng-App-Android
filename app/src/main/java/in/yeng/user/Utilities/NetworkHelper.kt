package `in`.yeng.user.Utilities

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.InputStream

object NetworkHelper {

    fun Download(link: String, func: (InputStream) -> Unit) {

        doAsync {
            val client = OkHttpClient()
            val request = Request.Builder().url(link).build()
            val response = client.newCall(request).execute()

            Log.d("xxx",link)

            uiThread {response.body()?.byteStream()?.let { func(it) } }
        }


    }
}
