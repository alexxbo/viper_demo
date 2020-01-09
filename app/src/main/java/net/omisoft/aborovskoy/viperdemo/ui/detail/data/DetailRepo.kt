package net.omisoft.aborovskoy.viperdemo.ui.detail.data

import io.reactivex.Single
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import net.omisoft.aborovskoy.viperdemo.ui.detail.DetailContract
import net.omisoft.aborovskoy.viperdemo.ui.detail.api.DetailApi

class DetailRepo(private val api: DetailApi) : DetailContract.Repo {

    override fun getPhoto(photoId: String): Single<PhotoModel> = api.getPhoto(photoId)
}