package `in`.yeng.user.team.network

import `in`.yeng.user.team.dom.GroupMember
import `in`.yeng.user.team.dom.Profile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamMembersReq {
    @GET("/amigosApi/mem/{id}")
    fun getUsersList(@Path("id") id: String): Call<List<GroupMember>>
}