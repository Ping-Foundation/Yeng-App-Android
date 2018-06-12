package `in`.yeng.user.team.network

import `in`.yeng.user.newsupdates.dom.NewsRes
import `in`.yeng.user.team.dom.Team
import retrofit2.Call
import retrofit2.http.GET

interface TeamReq {
    @GET("/getdata")
    fun getTeamList(): Call<List<Team>>
}