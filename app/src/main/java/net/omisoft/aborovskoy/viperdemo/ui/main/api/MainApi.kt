package net.omisoft.aborovskoy.viperdemo.ui.main.api

import io.reactivex.Single
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import retrofit2.http.GET

interface MainApi {

    @GET("v2/list?page=0&limit=100")
    fun getPhotos(): Single<List<PhotoModel>>
}