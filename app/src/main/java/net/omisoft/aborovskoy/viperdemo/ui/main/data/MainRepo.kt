package net.omisoft.aborovskoy.viperdemo.ui.main.data

import io.reactivex.Single
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import net.omisoft.aborovskoy.viperdemo.ui.main.MainContract
import net.omisoft.aborovskoy.viperdemo.ui.main.api.MainApi

class MainRepo(private val api: MainApi) : MainContract.Repo {

    override fun getPhotos(): Single<List<PhotoModel>> = api.getPhotos()
}