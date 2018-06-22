package `in`.yeng.user.team.dom

import com.google.gson.annotations.SerializedName


data class Profile(
        @SerializedName("group") val group: List<String>,
        @SerializedName("status") val status: Boolean,
        @SerializedName("_id") val id: String,
        @SerializedName("updatedOn") val updatedOn: String,
        @SerializedName("name") val name: String,
        @SerializedName("mob") val mob: String,
        @SerializedName("email") val email: String,
        @SerializedName("telegram") val telegram: String,
        @SerializedName("image") val image: String,
        @SerializedName("place") val place: String,
        @SerializedName("__v") val v: Int
)