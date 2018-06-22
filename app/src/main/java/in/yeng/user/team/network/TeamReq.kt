package `in`.yeng.user.team.network

import `in`.yeng.user.team.dom.Team
import retrofit2.Call
import retrofit2.http.GET

interface TeamReq {
    @GET("/amigosApi/teams")
    fun getTeamList(): Call<List<Team>>
}