package `in`.yeng.user.newsupdates.network

import `in`.yeng.user.newsupdates.dom.NewsRes
import retrofit2.Call
import retrofit2.http.GET

interface NewsReq {
    @GET("/getnews")
    fun getNews(): Call<List<NewsRes>>
}