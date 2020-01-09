package net.omisoft.aborovskoy.viperdemo.ui.detail.api

import io.reactivex.Single
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {

    @GET("/id/{id}/info")
    fun getPhoto(@Path("id") photoId: String): Single<PhotoModel>
}