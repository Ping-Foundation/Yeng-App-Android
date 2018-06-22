package `in`.yeng.user.team.network

import `in`.yeng.user.network.APIClient
import `in`.yeng.user.team.dom.GroupMember
import `in`.yeng.user.team.dom.Profile
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TeamMembersListAPI {
    fun withListOfTeamMembers(id: String, func: (List<GroupMember>) -> Unit) {
        doAsync {
            var client = APIClient.withURL(APIClient.CRAZY_AMIGOS_BASEURL)
            val memberService = client.create(TeamMembersReq::class.java)
            val call = memberService.getUsersList(id)
            val result = call.execute().body()
            uiThread { result?.let { func(result) } }
        }
    }
}