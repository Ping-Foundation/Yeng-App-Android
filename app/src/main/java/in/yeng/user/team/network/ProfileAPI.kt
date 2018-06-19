package `in`.yeng.user.team.network

import `in`.yeng.user.network.APIClient
import `in`.yeng.user.team.dom.Profile
import `in`.yeng.user.team.dom.Team
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


object ProfileAPI {
    fun withProfile(id: String, func: (Profile) -> Unit) {
        doAsync {
            val profileService = APIClient.withURL(APIClient.CRAZY_AMIGOS_BASEURL).create(ProfileReq::class.java)
            val call = profileService.getProfile(id)
            val result = call.execute().body()
            uiThread { result?.let { func(result) } }
        }
    }
}