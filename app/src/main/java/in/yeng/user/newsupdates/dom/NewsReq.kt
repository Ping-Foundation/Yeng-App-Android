package `in`.yeng.user.newsupdates.dom

import retrofit2.Call
import retrofit2.http.GET

interface NewsReq {
    @GET("/getnews")
    fun getNews(): Call<List<NewsRes>>
}