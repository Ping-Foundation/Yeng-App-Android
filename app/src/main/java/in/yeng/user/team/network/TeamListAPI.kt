package `in`.yeng.user.team.network

import `in`.yeng.user.network.APIClient
import `in`.yeng.user.team.dom.Team
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


object TeamListAPI {
    fun getTeamList(func: (List<Team>) -> Unit) {
        doAsync {
            val teamService = APIClient.withURL(APIClient.CRAZY_AMIGOS_BASEURL).create(TeamReq::class.java)
            val call = teamService.getTeamList()
            val result = call.execute().body()
            uiThread { result?.let { func(result) } }
        }
    }
}