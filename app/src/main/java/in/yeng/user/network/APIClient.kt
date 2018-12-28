package `in`.yeng.user.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {

    const val YENG_BASEURL = "https://www.yengapp.com"
    const val CRAZY_AMIGOS_BASEURL = "https://ca.yengapp.com"
//  const val YENG_BASEURL = "http://192.168.100.70:8080"
    //  const val CRAZY_AMIGOS_BASEURL = "http://192.168.100.70:8081"

    fun withURL(url: String): Retrofit {

        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    }

}
