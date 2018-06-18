package `in`.yeng.user.team.network

import `in`.yeng.user.team.dom.Profile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamMembersReq {
    @GET("getusers/{id}")
    fun getUsersList(@Path("id") id: String): Call<List<Profile>>
}