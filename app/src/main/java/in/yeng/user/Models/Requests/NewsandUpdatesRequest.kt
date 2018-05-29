package `in`.yeng.user.Models.Requests

import `in`.yeng.user.Models.Responses.NewsandUpdatesResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsandUpdatesRequest {
    @GET("/getnews")
    fun getNews(): Call<List<NewsandUpdatesResponse>>
}