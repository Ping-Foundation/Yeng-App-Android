package `in`.yeng.user.team.network

import `in`.yeng.user.team.dom.Profile
import `in`.yeng.user.team.dom.Team
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileReq {
    @GET("/amigosApi/mem/member/{id}")
    fun getProfile(@Path("id") id: String): Call<Profile>
}