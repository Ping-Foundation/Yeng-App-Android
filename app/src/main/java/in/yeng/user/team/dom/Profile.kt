package `in`.yeng.user.team.dom

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Profile(
        @SerializedName("_id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("photo") val profilePic: String,
        @SerializedName("designation") val designation: String,
        @SerializedName("description") val description: String,
        @SerializedName("mob") val mob: String,
        @SerializedName("email") val email: String,
        @SerializedName("address") val address: String
) : Serializable