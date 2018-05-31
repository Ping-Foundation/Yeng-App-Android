package `in`.yeng.user.newsupdates.dom

import com.google.gson.annotations.SerializedName

data class NewsRes(
        @SerializedName("_id") val id: String,
        @SerializedName("Tittle") val tittle: String,
        @SerializedName("News") val news: String,
        @SerializedName("DisplayDate") val displayDate: String,
        @SerializedName("EndDate") val endDate: String,
        @SerializedName("__v") val v: Int,
        @SerializedName("CreatedOn") val createdOn: String,
        @SerializedName("AttachmentName") val attachmentName: String?,
        @SerializedName("AttachmentPath") val attachmentPath: String?
)