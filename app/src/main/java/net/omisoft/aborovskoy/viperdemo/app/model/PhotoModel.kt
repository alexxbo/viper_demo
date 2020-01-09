package net.omisoft.aborovskoy.viperdemo.app.model

import com.google.gson.annotations.SerializedName

data class PhotoModel(@SerializedName("author") val author: String,
                      @SerializedName("download_url") val downloadUrl: String,
                      @SerializedName("height") val height: Int,
                      @SerializedName("id") val id: String,
                      @SerializedName("url") val url: String,
                      @SerializedName("width") val width: Int)