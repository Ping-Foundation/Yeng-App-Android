package `in`.yeng.user.team.network

import `in`.yeng.user.team.dom.Profile
import `in`.yeng.user.team.dom.Team
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TeamMembersListAPI {
    fun getTeamList(id: String, func: (List<Profile>) -> Unit) {
        doAsync {
            var client = Retrofit.Builder()
                    .baseUrl("http://demo6847025.mockable.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val memberService = client.create(TeamMembersReq::class.java)
            val call = memberService.getUsersList(id)
            val result = call.execute().body()
            uiThread { result?.let { func(result) } }
        }
    }
}