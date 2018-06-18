package `in`.yeng.user.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    const val YENG_BASEURL = "https://www.yengapp.com"
    const val CRAZY_AMIGOS_BASEURL = "http://192.168.100.70:3000"

    fun withURL(url: String): Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
