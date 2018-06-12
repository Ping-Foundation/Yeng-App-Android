package `in`.yeng.user.team.network

import `in`.yeng.user.team.dom.Team
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TeamListAPI {
    fun getTeamList(func: (List<Team>) -> Unit) {
        doAsync {
            var client = Retrofit.Builder()
                    .baseUrl("http://demo6847025.mockable.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val teamService = client.create(TeamReq::class.java)
            val call = teamService.getTeamList()
            val result = call.execute().body()
            uiThread { result?.let { func(result) } }
        }
    }
}