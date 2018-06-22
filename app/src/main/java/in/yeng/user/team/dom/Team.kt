package `in`.yeng.user.team.dom

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
        @SerializedName("_id") val id: String,
        @SerializedName("groups") val groups: List<Group>,
        @SerializedName("name") val name: String,
        @SerializedName("__v") val v: Int
)

data class Group(
        @SerializedName("_id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("icon") val icon: String
) : Serializable